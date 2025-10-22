package com.test.practiceProject.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class CryptoService {
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int IV_LENGTH_BYTES = 12;

    private final byte[] keyBytes;
    private final SecureRandom secureRandom = new SecureRandom();

    public CryptoService(@Value("${security.redis.encryption-key:}") String base64Key) {
        if (base64Key == null || base64Key.isEmpty()) {
            // Fallback: generate an ephemeral key if none provided (NOT recommended for multi-instance)
            try {
                KeyGenerator kg = KeyGenerator.getInstance("AES");
                kg.init(256);
                SecretKey sk = kg.generateKey();
                this.keyBytes = sk.getEncoded();
            } catch (Exception e) {
                throw new IllegalStateException("Cannot initialize encryption key", e);
            }
        } else {
            this.keyBytes = Base64.getDecoder().decode(base64Key);
        }
        if (this.keyBytes.length != 16 && this.keyBytes.length != 24 && this.keyBytes.length != 32) {
            throw new IllegalStateException("Invalid AES key length. Provide 128/192/256-bit base64 key.");
        }
    }

    public String encryptToBase64(String plaintext) {
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            ByteBuffer buffer = ByteBuffer.allocate(iv.length + ciphertext.length);
            buffer.put(iv);
            buffer.put(ciphertext);
            return Base64.getEncoder().encodeToString(buffer.array());
        } catch (Exception e) {
            throw new IllegalStateException("Encryption failed", e);
        }
    }

    public String decryptFromBase64(String base64Ciphertext) {
        try {
            byte[] all = Base64.getDecoder().decode(base64Ciphertext);
            if (all.length < IV_LENGTH_BYTES + 1) {
                throw new IllegalArgumentException("Ciphertext too short");
            }
            byte[] iv = new byte[IV_LENGTH_BYTES];
            byte[] ciphertext = new byte[all.length - IV_LENGTH_BYTES];
            System.arraycopy(all, 0, iv, 0, IV_LENGTH_BYTES);
            System.arraycopy(all, IV_LENGTH_BYTES, ciphertext, 0, ciphertext.length);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            byte[] plain = cipher.doFinal(ciphertext);
            return new String(plain, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Decryption failed", e);
        }
    }
}



