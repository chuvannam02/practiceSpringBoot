package com.test.practiceProject.controller;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:44 AM
 */

import com.test.practiceProject.dto.in.FileMetadata;
import com.test.practiceProject.service.factory.NotificationFactoryAbstract;
import com.test.practiceProject.service.factory.NotificationFactoryRegistry;
import com.test.practiceProject.utils.enums.NotificationType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class TestController {

    private final NotificationFactoryRegistry registryFactory;
    private final NotificationFactoryAbstract lookupFactory;

    public TestController(NotificationFactoryRegistry registryFactory,
                          NotificationFactoryAbstract lookupFactory) {
        this.registryFactory = registryFactory;
        this.lookupFactory = lookupFactory;
    }

    @GetMapping("/test/registry")
    public String testRegistry() {
        var s1 = registryFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello");
        var s2 = registryFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello again");
        return "Registry:\n" + s1 + "\n" + s2;
    }

    @GetMapping("/test/lookup")
    public String testLookup() {
        var s1 = lookupFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello");
        var s2 = lookupFactory.getService(NotificationType.EMAIL).sendWithReturn("Hello again");
        return "Lookup:\n" + s1 + "\n" + s2;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadFile(
        @RequestPart("metadata") FileMetadata metadata,
        @RequestPart("file") MultipartFile file
    ) {
        // In ra thông tin
        System.out.println("Title: " + metadata.getTitle());
        System.out.println("Description: " + metadata.getDescription());
        System.out.println("File name: " + file.getOriginalFilename());

        // Trả về response JSON
        return ResponseEntity.ok(Map.of(
            "title", metadata.getTitle(),
            "fileName", file.getOriginalFilename(),
            "size", file.getSize()
        ));
    }
}
