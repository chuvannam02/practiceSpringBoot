package com.test.practiceProject.Utils.Enums;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Enums  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:42 AM
 */

public enum Severity {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String level;

    Severity(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String getUppercaseLevel() {
        return level.toUpperCase();
    }
}
