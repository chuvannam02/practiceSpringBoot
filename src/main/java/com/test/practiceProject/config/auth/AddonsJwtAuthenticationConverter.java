package com.test.practiceProject.config.auth;

import com.test.practiceProject.repository.Angular.RoleMenuRepository;
import org.springframework.core.convert.converter.Converter;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config.auth  *
 * @Author: ChuVanNam
 * @Date: 3/16/2025
 * @Time: 4:37 PM
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddonsJwtAuthenticationConverter implements Converter<String, JwtAuth> {

    private final RoleMenuRepository roleMenuRepository;

    // Bí mật dùng để ký và xác thực JWT (đảm bảo bí mật này chỉ server biết)
    private static final String JWT_SECRET = "67566B59703573367639792F423F4528482B4D6251655468576D5A712904200112312312321";

    @Override
    public JwtAuth convert(String token) {
        try {
            Key key = getSignKey();
            Jws<Claims> jwsClaims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            Claims claims = jwsClaims.getBody();

            // Trích xuất username từ claims, ví dụ dùng trường "preferred_username"
            String username = claims.get("preferred_username", String.class);

            // Chuyển đổi authorities từ claims thông qua converter tùy chỉnh
            List<String> authorities = new JwtGrantedAuthoritiesConverter(roleMenuRepository)
                    .convert(claims);

            return new JwtAuth(token, username, authorities, claims);
        } catch (Exception e) {
            log.error("Error converting JWT token", e);
            return null;
        }
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}