package com.test.practiceProject.config;

import jakarta.validation.MessageInterpolator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Map;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config  *
 * @Author: ChuVanNam
 * @Date: 3/14/2025
 * @Time: 2:13 PM
 */

@Slf4j
public class CustomMessageInterpolator implements MessageInterpolator {

    // MessageSource là một interface cung cấp các phương thức để đọc thông điệp từ các tài nguyên khác nhau như file properties, database, ...
    // Nhờ đó, chúng ta có thể đọc thông điệp từ properties file tùy theo cấu hình của ứng dụng
    private final MessageSource messageSource;

    public CustomMessageInterpolator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        // Mặc định sử dụng locale mặc định của hệ thống
        // Để sử dụng locale khác, chúng ta có thể truyền vào tham số thứ 2
        // Ví dụ: interpolate(messageTemplate, context, new Locale("vi", "VN"))
        // Hoặc interpolate(messageTemplate, context, new Locale("en", "US"))
        // Hoặc interpolate(messageTemplate, context, new Locale("fr", "FR"))
        // Hoặc interpolate(messageTemplate, context, new Locale("ja", "JP"))
        return interpolate(messageTemplate, context, Locale.getDefault());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        // 1. Resolve từ properties file (messageTemplate = {NotBlank.field})
        String resolvedMessage = messageSource.getMessage(
                stripBraces(messageTemplate), null, messageTemplate, locale
        );

        log.info("resolvedMessage: {}", resolvedMessage);

        // 2. Replace placeholders
        Map<String, Object> attributes = context.getConstraintDescriptor().getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            log.info("key: {}, value: {}", key, value);
            if (value != null) {
                resolvedMessage = resolvedMessage.replace("{" + key + "}", value.toString());
            }
        }

        return resolvedMessage;
    }

    private String stripBraces(String template) {
        if (template.startsWith("{") && template.endsWith("}")) {
            return template.substring(1, template.length() - 1);
        }
        return template;
    }
}

