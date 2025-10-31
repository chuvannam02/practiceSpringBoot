package com.test.practiceProject.tests;

import com.test.practiceProject.publisher.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.tests  *
 * @Author: ChuVanNam
 * @Date: 10/28/2025
 * @Time: 4:40 PM
 */

@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @Test
    public void testSendMessageToKafka() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        String message = "Chu Văn Nam";
//        kafkaMessagePublisher.sendMessageToTopic(message);
//        kafkaMessagePublisher.sendMessageToTopic();

        for (int i = 1; i <= 10000; i++) {
            kafkaMessagePublisher.sendMessageToTopic(message + " : " + i);
        }
    }
}
