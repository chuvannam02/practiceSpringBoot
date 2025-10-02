package com.test.practiceProject.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:17 AM
 */

public interface Loggable {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String format();

    default String getTimestamp() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
