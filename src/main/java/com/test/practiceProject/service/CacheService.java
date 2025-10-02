package com.test.practiceProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.service  *
 * @Author: ChuVanNam
 * @Date: 10/3/2025
 * @Time: 12:40 AM
 */

@Service
public class CacheService {
    @Value("${spring.data.redis.time-to-live}")
    private Long redisTTL = 5*60*1000L;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveWithTTL(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

