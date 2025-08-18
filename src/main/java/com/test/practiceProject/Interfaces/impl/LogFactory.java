package com.test.practiceProject.Interfaces.impl;

import com.test.practiceProject.Interfaces.Loggable;
import com.test.practiceProject.Utils.Enums.Severity;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces.impl  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 11:17 AM
 */

public class LogFactory {
    public static Loggable create(String type, String message) {
        return switch (type) {
            case "TEXT" -> new TextLog(message);
            case "FILE" -> new FileLog("/", message);
            case "CONSOLE" -> new ConsoleLog(Severity.HIGH, message);
            default -> throw new IllegalArgumentException("Unknown log type");
        };
    }
}
