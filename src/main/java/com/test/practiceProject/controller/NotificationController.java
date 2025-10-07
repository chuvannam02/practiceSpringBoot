package com.test.practiceProject.controller;

import com.test.practiceProject.service.NotificationService;
import com.test.practiceProject.service.factory.NotificationFactory;
import com.test.practiceProject.utils.enums.NotificationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:33 AM
 */

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationFactory factory;

    public NotificationController(NotificationFactory factory) {
        this.factory = factory;
    }

    @GetMapping
    public String send(@RequestParam NotificationType type, @RequestParam String message) {
        NotificationService service = factory.getService(type);
        service.send(message);
        return "Sent " + type + " notification successfully!";
    }
}
