package com.test.practiceProject.config.redis;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config  *
 * @Author: ChuVanNam
 * @Date: 10/3/2025
 * @Time: 12:43 AM
 */

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    //    @Value("${spring.data.redis.time-to-live}")
//    private Long redisTTL = 5*60*1000L;
    private final RedisProperties redisProperties;

    public RedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager.builder(factory)
            .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(5))) // TTL mặc định 5 phút
//                .entryTtl(Duration.ofMillis(redisTTL)))
                .entryTtl(Duration.ofMillis(redisProperties.getTimeToLive())))
            .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();

        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.setHashValueSerializer(valueSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
