package com.test.practiceProject.tests;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.tests  *
 * @Author: ChuVanNam
 * @Date: 9/30/2025
 * @Time: 10:48 AM
 */

import com.test.practiceProject.Service.factory.NotificationFactory;
import com.test.practiceProject.Utils.Enums.NotificationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationFactoryTest {

    @Autowired
    private NotificationFactory factory;

    @Test
    void givenPrototypeScope_whenGetService_thenDifferentInstances() {
        var s1 = factory.getService(NotificationType.EMAIL);
        var s2 = factory.getService(NotificationType.EMAIL);

        // cùng type nhưng phải khác instance
        assertThat(s1).isNotSameAs(s2);

        // check send() tạo chuỗi khác (UUID khác)
        String msg1 = s1.sendWithReturn("Hello");
        String msg2 = s2.sendWithReturn("Hello Again");

        assertThat(msg1).isNotEqualTo(msg2);
    }
}
