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
// messageInterpolator là một interface cung cấp các phương thức để tùy chỉnh thông điệp lỗi trả về từ các annotation validation
// Chúng ta có thể tùy chỉnh thông điệp lỗi trả về từ các annotation validation như @NotNull, @NotBlank, @Size, @Email, ...
// Bằng cách implement interface MessageInterpolator và override phương thức interpolate
// Trong phương thức interpolate, chúng ta có thể đọc thông điệp từ các tài nguyên khác nhau như file properties, database, ...
// Để đọc thông điệp từ file properties, chúng ta cần sử dụng interface MessageSource
// MessageSource là một interface cung cấp các phương thức để đọc thông điệp từ các tài nguyên khác nhau như file properties, database, ...
// Nhờ đó, chúng ta có thể đọc thông điệp từ properties file tùy theo cấu hình của ứng dụng
// Trong phương thức interpolate, chúng ta cũng có thể thay thế các placeholder trong thông điệp lỗi bằng các giá trị tương ứng
// Ví dụ: {NotBlank.field} -> {NotBlank.field} -> {NotBlank.username} -> {NotBlank.password} -> {NotBlank.email}
// Để sử dụng custom message interpolator, chúng ta cần cấu hình trong file application.properties
// spring.mvc.messageCodesResolver=org.springframework.validation.DefaultMessageCodesResolver
// spring.mvc.messageCodesResolverFormat=POSTFIX_ERROR_CODE
// spring.mvc.validation.message.interpolator.enabled=true
// spring.mvc.validation.message.interpolator=org.springframework.validation.DefaultMessageInterpolator
// spring.messages.basename=messages
// Trong đó, spring.messages.basename=messages là tên file properties chứa thông điệp lỗi
// Ví dụ: messages.properties, messages_en.properties, messages_vi.properties, messages_fr.properties, messages_ja.properties
// Trong file properties, chúng ta có thể định nghĩa thông điệp lỗi tùy chỉnh cho các annotation validation
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
        log.info("messageTemplate: {}", messageTemplate);
        log.info("context: {}", context);
        log.info("locale: {}", locale);
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

    /**
     * @param template {NotBlank.field}
     *                 {NotBlank.field} -> NotBlank.field
     *                 {NotBlank.username} -> NotBlank.username
     *                 {NotBlank.password} -> NotBlank.password
     * @return
     * @description: Xóa dấu ngoặc nhọn ở đầu và cuối chuỗi
     * @version: 1.0
     */
    private String stripBraces(String template) {
        if (template.startsWith("{") && template.endsWith("}")) {
            return template.substring(1, template.length() - 1);
        }
        return template;
    }
}

