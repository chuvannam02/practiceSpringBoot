package com.test.practiceProject.Controller;

import com.test.practiceProject.Service.factory.NotificationFactoryProvider;
import com.test.practiceProject.Utils.Enums.NotificationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:47 AM
 */

@RestController
public class TestControllerFactory {
    private final NotificationFactoryProvider providerFactory;

    public TestControllerFactory(NotificationFactoryProvider providerFactory) {
        this.providerFactory = providerFactory;
    }

    @GetMapping("/test/provider")
    public String testProvider() {
        var s1 = providerFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello");
        var s2 = providerFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello again");
        return "Provider:\n" + s1 + "\n" + s2;
    }
}
