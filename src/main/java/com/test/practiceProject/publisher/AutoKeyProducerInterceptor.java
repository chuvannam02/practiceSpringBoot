package com.test.practiceProject.publisher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.publisher  *
 * @Author: ChuVanNam
 * @Date: 10/28/2025
 * @Time: 8:53 PM
 */
//Cấu hình ProducerRecordInterceptor
//Bạn có thể định nghĩa interceptor để tự động gán key trước khi gửi:
@Component
@Slf4j
public class AutoKeyProducerInterceptor implements ProducerInterceptor<String, Object> {
    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
        log.info(String.valueOf(record));
        if (record.key() == null) {
            String key = UUID.randomUUID().toString();
            return new ProducerRecord<>(record.topic(), key, record.value());
        }
        return record;
    }

    /**
     * @param recordMetadata
     * @param e
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    /**
     *
     */
    @Override
    public void close() {

    }

    /**
     * @param map
     */
    @Override
    public void configure(Map<String, ?> map) {

    }
}

