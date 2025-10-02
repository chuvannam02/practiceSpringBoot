package com.test.practiceProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

/**
 * Service managing refresh tokens and access-token blacklist in Redis for high performance.
 */
@Service
public class RefreshTokenService {
    @Value("${security.jwt.access-ttl-seconds:900}")
    private long accessTtlSeconds;

    @Value("${security.jwt.refresh-ttl-seconds:1209600}") // 14 days default
    private long refreshTtlSeconds;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RefreshTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String issueRefreshToken(String subject) {
        String tokenId = UUID.randomUUID().toString();
        String key = buildRefreshKey(tokenId);
        // store subject as value for quick lookup/validation
        redisTemplate.opsForValue().set(key, subject, Duration.ofSeconds(refreshTtlSeconds));
        return tokenId;
    }

    public String resolveSubjectByRefreshToken(String tokenId) {
        String key = buildRefreshKey(tokenId);
        return redisTemplate.opsForValue().get(key);
    }

    public void revokeRefreshToken(String tokenId) {
        redisTemplate.delete(buildRefreshKey(tokenId));
    }

    public void blacklistAccessToken(String jti, long remainingSeconds) {
        if (remainingSeconds <= 0) return;
        String key = buildBlacklistKey(jti);
        redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(remainingSeconds));
    }

    public boolean isAccessTokenBlacklisted(String jti) {
        String key = buildBlacklistKey(jti);
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    private String buildRefreshKey(String tokenId) {
        return "auth:refresh:" + tokenId;
    }

    private String buildBlacklistKey(String jti) {
        return "auth:blacklist:" + jti;
    }
}


