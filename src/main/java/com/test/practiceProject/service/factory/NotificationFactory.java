package com.test.practiceProject.service.factory;

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.utils.Enums.NotificationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.factory  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:32 AM
 */

@Component
public class NotificationFactory {
    // EnumMap là gì?
//    private final Map<NotificationType, NotificationService> serviceMap = new EnumMap<>(NotificationType.class);

    //    public NotificationFactory(
//            EmailNotificationService emailService,
//            SmsNotificationService smsService,
//            PushNotificationService pushService) {
//        serviceMap.put(NotificationType.EMAIL, emailService);
//        serviceMap.put(NotificationType.SMS, smsService);
//        serviceMap.put(NotificationType.PUSH, pushService);
//    }
//    public NotificationFactory(
//            @Qualifier("emailNotificationService") NotificationService emailService,
//            @Qualifier("smsNotificationService") NotificationService smsService,
//            @Qualifier("pushNotificationService") NotificationService pushService) {
//
//        serviceMap.put(NotificationType.EMAIL, emailService);
//        serviceMap.put(NotificationType.SMS, smsService);
//        serviceMap.put(NotificationType.PUSH, pushService);
//    }

    //    👉 Factory tự động build Map nhờ Spring:
//    ➡ Ưu điểm:
//    Không cần sửa factory khi thêm service mới → chỉ cần implement interface + @Service.
//    Rất phù hợp với Spring Boot (plug-and-play).
//            ➡ Nhược điểm: khó debug hơn nếu có nhiều service cùng NotificationType.
    private final Map<NotificationType, NotificationService> serviceMap;
    public NotificationFactory(List<NotificationService> services) {
        serviceMap = services.stream()
                .collect(Collectors.toMap(NotificationService::getType, s -> s));
    }

    // Cách 1: NotificationFactory xử lý trả về Notification Service phù hợp
    public NotificationService getService(NotificationType type) {
        return serviceMap.get(type);
    }
}
//        👉 Với @Scope("prototype"), khi Spring inject vào constructor thì nó vẫn chỉ giữ instance đầu tiên.
//        ➡️ Tức là lần nào gọi cũng trả về cùng 1 object → prototype không có tác dụng ở đây.
//🔹 Cách 2: Dynamic Registry (inject List/Map rồi build)
//@Component
//public class NotificationFactory {
//    private final Map<NotificationType, NotificationService> serviceMap;
//
//    public NotificationFactory(List<NotificationService> services) {
//        serviceMap = services.stream()
//                .collect(Collectors.toMap(NotificationService::getType, s -> s));
//    }
//
//    public NotificationService getService(NotificationType type) {
//        return serviceMap.get(type);
//    }
//}
//
//    Đặc điểm:
//
//        Lifecycle: Spring inject sẵn singleton instance của mỗi NotificationService.
//
//        Factory chỉ trỏ tới các bean singleton có sẵn.
//
//        Khi gọi getService(), bạn nhận cùng một instance mỗi lần.
//
//        👉 Ví dụ:
//
//        Nếu EmailNotificationService có state (count gửi email), thì state đó giữ nguyên trong suốt vòng đời app.
//
//        Tốt cho stateless service (hầu hết service trong Spring đều là singleton).
//
//        Performance cao vì không tạo mới object.


//🔹 Cách 3: @Lookup
//@Component
//public abstract class NotificationFactory {
//    public NotificationService getService(NotificationType type) {
//        return switch (type) {
//            case EMAIL -> emailService();
//            case SMS -> smsService();
//            case PUSH -> pushService();
//        };
//    }
//
//    @Lookup
//    protected abstract NotificationService emailService();
//
//    @Lookup
//    protected abstract NotificationService smsService();
//
//    @Lookup
//    protected abstract NotificationService pushService();
//}
//
//    Đặc điểm:
//
//@Lookup bảo Spring: mỗi lần gọi method thì hãy lấy bean mới từ context.
//
//        Nếu NotificationService có scope prototype, Spring sẽ new instance mỗi lần.
//
//        Nếu NotificationService là singleton, thì Spring vẫn chỉ return instance cũ (giống cách 2).
//
//        👉 Ví dụ:
//
//        Nếu EmailNotificationService cần tạo kết nối tạm thời, hoặc chứa state riêng cho từng request, thì @Lookup rất phù hợp.
//
//        Giúp factory luôn trả về instance fresh.
//
//        Performance thấp hơn cách 2 (do phải lookup từ Spring context).