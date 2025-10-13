package com.test.practiceProject.utils;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 12:22 PM
 */

public class DataFormat {
    public static String formatString(String str) {
        return str.trim().replaceAll("\\s+", " ");
    }

    public static String trim(String str) {
        if (!str.isBlank()) {
            return str.trim();
        }

        return null;
    }
}
