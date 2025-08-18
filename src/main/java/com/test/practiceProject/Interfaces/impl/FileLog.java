package com.test.practiceProject.Interfaces.impl;

import com.test.practiceProject.Interfaces.Loggable;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces.impl  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:40 AM
 */

public class FileLog implements Loggable {
    private final String filePath;
    private final String message;

    public FileLog(String filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }

    @Override
    public String format() {
        return String.format("File: %s, Time: %s, Message: %s", filePath, getTimestamp(), message);
    }
}
