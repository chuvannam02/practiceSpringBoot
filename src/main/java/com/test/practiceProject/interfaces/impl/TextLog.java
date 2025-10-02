package com.test.practiceProject.interfaces.impl;

import com.test.practiceProject.interfaces.Loggable;
import lombok.RequiredArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces.impl  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:38 AM
 */

@RequiredArgsConstructor
public class TextLog implements Loggable {
    private final String message;

    @Override
    public String format() {
        return String.format("%s: %s", getTimestamp(), message);
    }
}
