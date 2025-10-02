package com.test.practiceProject.service;

import com.test.practiceProject.utils.Enums.NotificationType;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:25 AM
 */

public interface NotificationService {
    // Cách 2: Tự động đăng ký Service theo Enum (Dynamic Registry)
    NotificationType getType(); // 👈 service tự cho biết loại
    NotificationService  getType(NotificationType  type);
    void send(String message);
    String sendWithReturn(String message);
}
