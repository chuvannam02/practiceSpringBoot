package com.test.practiceProject.service.impl;

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.utils.Enums.NotificationType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.impl  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:28 AM
 */

@Service("emailNotificationService")
@Scope("prototype") // mỗi lần get sẽ là instance mới
public class EmailNotificationService implements NotificationService {
    private final String id = UUID.randomUUID().toString();
    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }
    /**
     * @param type
     * @return
     */
    @Override
    public NotificationService getType(NotificationType type) {
        return null;
    }

    /**
     * @param message
     * @return
     */
    @Override
    public void send(String message) {
        System.out.println("📧 Sending EMAIL: " + message);
    }
    @Override
    public String sendWithReturn(String message) {
        return "[EMAIL-" + id + "] " + message;
    }

}
