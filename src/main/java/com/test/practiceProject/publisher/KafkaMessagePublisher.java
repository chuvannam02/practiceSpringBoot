package com.test.practiceProject.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.publisher  *
 * @Author: ChuVanNam
 * @Date: 10/28/2025
 * @Time: 4:27 PM
 */

@Service
@Slf4j
public class KafkaMessagePublisher {
    // to publish a message/ event  to the Kafka or from our application, if you want to talk to the Kafka Server
    // => Use KafkaTemplate<Key, Value>
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message) {
        // kafkaTemplate.send(<name of topic>, <messages are sent into topic>)
//        kafkaTemplate.send("test-topic", message);
        // Tuy nhiên kiểu trả về của method này là CompletableFuture<SendResult<K, V>> => CompletableFuture<SendResult<String, Object>>
        // Nếu trong kafka chưa có topic "test-topic" thì Spring sẽ đứng ra tạo thay chúng ta
        String keyV1 = UUID.randomUUID().toString();
        String keyV2 = String.valueOf(message.hashCode());
//        kafkaTemplate.send(<topic-name>, <key-name>, <message>);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic", message);
        future.whenComplete((result, ex) -> {
            log.info(String.valueOf(result.getRecordMetadata().partition()));
            if (ex == null) {
                System.out.println("Sent message = [" + message + "] with offset = [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message = [" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
