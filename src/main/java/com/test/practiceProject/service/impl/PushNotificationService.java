package com.test.practiceProject.service.impl;

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.utils.enums.NotificationType;
import org.springframework.stereotype.Service;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.impl  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:29 AM
 */

@Service("pushNotificationService")
public class PushNotificationService implements NotificationService {
    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
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
        System.out.println("🔔 Sending PUSH: " + message);
    }

    /**
     * @param message
     * @return
     */
    @Override
    public String sendWithReturn(String message) {
        return null;
    }
}
