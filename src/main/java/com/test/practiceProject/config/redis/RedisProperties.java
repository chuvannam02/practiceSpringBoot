package com.test.practiceProject.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config.redis  *
 * @Author: ChuVanNam
 * @Date: 10/3/2025
 * @Time: 1:36 AM
 */

@ConfigurationProperties(prefix = "spring.data.redis")
@Data
public class RedisProperties {
    private String host;
    private int port;
    private long timeout;
    private long timeToLive;

    // getter/setter
}