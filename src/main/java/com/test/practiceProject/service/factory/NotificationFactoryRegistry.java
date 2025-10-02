package com.test.practiceProject.service.factory;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.factory  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:44 AM
 */

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.utils.Enums.NotificationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationFactoryRegistry {

    private final Map<NotificationType, NotificationService> serviceMap;

    public NotificationFactoryRegistry(List<NotificationService> services) {
        this.serviceMap = services.stream()
                .collect(Collectors.toMap(NotificationService::getType, s -> s));
    }

    public NotificationService getService(NotificationType type) {
        return serviceMap.get(type);
    }
}

//👉 Với @Scope("prototype"), khi Spring inject vào constructor thì nó vẫn chỉ giữ instance đầu tiên.
//        ➡️ Tức là lần nào gọi cũng trả về cùng 1 object → prototype không có tác dụng ở đây.
