package com.test.practiceProject.service.factory;

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.utils.enums.NotificationType;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.factory  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:39 AM
 */

@Component
public abstract class NotificationFactoryAbstract {

    public NotificationService getService(NotificationType type) {
        return switch (type) {
            case EMAIL -> emailService();
            case SMS -> smsService();
            case TEGEGRAM -> null;
            case SLACK -> null;
            case ZALO -> null;
            case MESSENGER -> null;
            case PUSH -> pushService();
        };
    }

//    👉 Mỗi lần gọi emailService() → Spring sẽ tạo prototype instance mới → UUID khác nhau.
    @Lookup
    protected abstract NotificationService emailService();

    @Lookup
    protected abstract NotificationService smsService();

    @Lookup
    protected abstract NotificationService pushService();
}


//        ➡ Ưu điểm: Spring quản lý lifecycle (new instance mỗi lần).
//        ➡ Nhược điểm: code nhiều hơn, ít linh hoạt hơn cách 2.

//        ✅ Kết luận:
//        Nếu service singleton → dùng Cách 2 (dynamic registry) là tối ưu nhất.
//        Nếu service cần prototype hoặc tạo mới mỗi lần → dùng Cách 3 (@Lookup).
