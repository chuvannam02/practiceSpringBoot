package com.test.practiceProject.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.test.practiceProject.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.consumer  *
 * @Author: ChuVanNam
 * @Date: 10/29/2025
 * @Time: 11:36 AM
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageConsumer {
    private final ObjectMapper objectMapper;

    private final List<String> restrictedIpList = Stream.of(
        "32.241.244.236",
        "15.55.49.164",
        "81.1.95.253",
        "126.130.43.183"
    ).collect(Collectors.toList());

    // Dù chỉ có duy nhất 1 consumer nhưng vẫn cần map với 1 consumer_group_id
    @KafkaListener(
        topics = "test-topic",
        groupId = "test",
        concurrency = "2" // tối đa = số partition
    )
    public void consume(String message) {
        log.info("Consumer consume the message {} ", message);
    }

    /**
     * Consumer chính với RetryableTopic
     */
    @RetryableTopic(
        attempts = "4", // 1 primary + 3 retry
        backoff = @Backoff(delay = 1500, multiplier = 2, maxDelay = 6000),
        dltStrategy = DltStrategy.FAIL_ON_ERROR,
        include = {RuntimeException.class, RetriableException.class}
    )
    @KafkaListener(topics = "test-topic", groupId = "test-consumer-group", concurrency = "2")
    @Transactional()
    public void consumeUserEvent(@Payload String message,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                 @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Processing message: topic={}, partition={}, offset={}", topic, partition, offset);
        log.info("Message: {}", message);

        try {
            if (!StringUtils.hasText(message)) {
                throw new RuntimeException("Message content cannot be empty");
            }

            // Deserialize User object, xử lý trường hợp double-serialized
            User user;
            try {
                user = objectMapper.readValue(message, User.class);
            } catch (MismatchedInputException e) {
                log.warn("Message double-serialized, attempting to unwrap...");
                String unwrapped = objectMapper.readValue(message, String.class);
                user = objectMapper.readValue(unwrapped, User.class);
            }

            // Kiểm tra restricted IP
            if (restrictedIpList.contains(user.getIpAddress())) {
                throw new RuntimeException("Invalid IP Address received!");
            }

            log.info("Successfully processed User: {}", user);

        } catch (JsonProcessingException e) {
            log.error("Failed to parse message: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to parse message", e);
        } catch (Exception e) {
            log.error("Failed to process message: {}", e.getMessage(), e);
            throw e; // đẩy lên để RetryableTopic retry
        }
    }

    /**
     * Dead Letter Topic handler khi retry hết
     */
    @DltHandler
    public void handleFailedMessage(@Payload String failedMessage,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                    @Header(KafkaHeaders.OFFSET) long offset,
                                    @Header(KafkaHeaders.EXCEPTION_MESSAGE) String exceptionMessage) {
        log.error("Message processing ultimately failed: topic={}, partition={}, offset={}", topic, partition, offset);
        log.error("Failed message: {}", failedMessage);
        log.error("Exception: {}", exceptionMessage);
        // TODO: có thể gửi email cảnh báo hoặc lưu vào DB
    }
}
