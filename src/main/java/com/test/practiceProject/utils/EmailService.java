package com.test.practiceProject.utils;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.utils  *
 * @Author: ChuVanNam
 * @Date: 10/25/2025
 * @Time: 2:22 PM
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    // Inject JavaMailSender từ cấu hình Spring Boot
    private final JavaMailSender javaMailSender;

    private final TemplateEngine emailTemplateEngine;

    // Lấy địa chỉ email mặc định (người gửi) từ application.yml hoặc .properties
    @Value("${spring.mail.username}")
    private String defaultFrom;

    /**
     * Gửi email đơn giản (text/plain)
     *
     * @param to      Người nhận
     * @param subject Tiêu đề email
     * @param body    Nội dung email
     */
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(defaultFrom); // dùng email cấu hình trong spring.mail.username
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);

            log.info("✅ Email đã gửi thành công đến {}", to);
        } catch (Exception ex) {
            log.error("❌ Lỗi khi gửi email: {}", ex.getMessage(), ex);
        }
    }

    public void sendWelcomeEmail(String to, String name, Locale locale) {
        try {
            // Chuẩn bị biến context cho template
            Context context = new Context(locale);
            context.setVariable("name", name);
            context.setVariable("supportEmail", "support@yourapp.com");
            context.setVariable("logoUrl", "cid:logoImage");

            String htmlContent = emailTemplateEngine.process("welcome-email", context);

            // Tạo message
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(defaultFrom);
            helper.setTo(to);
            helper.setSubject("🎉 Welcome to Our Service!");
            helper.setText(htmlContent, true);

            // Đính kèm logo (embedded image)
            helper.addInline("logoImage", new org.springframework.core.io.ClassPathResource("static/images/logo.png"));

            javaMailSender.send(message);

            log.info("✅ Email HTML đã gửi đến {}", to);
        } catch (Exception e) {
            log.error("❌ Lỗi khi gửi email HTML: {}", e.getMessage(), e);
        }
    }
}

