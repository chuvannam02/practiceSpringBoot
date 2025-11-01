package com.test.practiceProject.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config  *
 * @Author: ChuVanNam
 * @Date: 10/28/2025
 * @Time: 9:17 PM
 */

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic mainTopic() {
        return TopicBuilder.name("lake-notification-topic")
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name("lake-notification-topic.DLT")
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name("test-topic")
            .partitions(2)
            .replicas(1)
            .build();
    }
}
