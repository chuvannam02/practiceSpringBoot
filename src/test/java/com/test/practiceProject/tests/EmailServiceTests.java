package com.test.practiceProject.tests;

import com.test.practiceProject.utils.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.tests  *
 * @Author: ChuVanNam
 * @Date: 10/25/2025
 * @Time: 3:06 PM
 */

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("namcv12@gmail.com", "Testing java mail sender", "Hi, How are you?");
    }

    @Test
    void sendWelcomeEmail() {
        emailService.sendWelcomeEmail("namcv12@gmail.com", "Chu Văn Nam", Locale.getDefault());
    }
}
