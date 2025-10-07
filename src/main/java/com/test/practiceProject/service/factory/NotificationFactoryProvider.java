package com.test.practiceProject.service.factory;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.factory  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:46 AM
 */

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.service.impl.EmailNotificationService;
import com.test.practiceProject.service.impl.PushNotificationService;
import com.test.practiceProject.service.impl.SmsNotificationService;
import com.test.practiceProject.utils.enums.NotificationType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

//Cách 4: kết hợp ObjectProvider<NotificationService> (giống lazy lookup, gọn hơn @Lookup) để tối ưu
@Component
public class NotificationFactoryProvider {

    private final Map<NotificationType, ObjectProvider<NotificationService>> serviceProviders =
            new EnumMap<>(NotificationType.class);

    public NotificationFactoryProvider(
            ObjectProvider<EmailNotificationService> emailProvider,
            ObjectProvider<SmsNotificationService> smsProvider,
            ObjectProvider<PushNotificationService> pushProvider) {

        serviceProviders.put(NotificationType.EMAIL, (ObjectProvider) emailProvider);
        serviceProviders.put(NotificationType.SMS, (ObjectProvider) smsProvider);
        serviceProviders.put(NotificationType.PUSH, (ObjectProvider) pushProvider);
    }

    public NotificationService getService(NotificationType type) {
        return serviceProviders.get(type).getObject(); // luôn trả instance mới nếu scope=prototype
    }
}
