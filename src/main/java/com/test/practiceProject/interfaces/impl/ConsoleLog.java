package com.test.practiceProject.interfaces.impl;

import com.test.practiceProject.interfaces.Loggable;
import com.test.practiceProject.utils.enums.Severity;
import lombok.RequiredArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces.impl  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:41 AM
 */

@RequiredArgsConstructor
public class ConsoleLog implements Loggable {
    private final Severity severity;
    private final String message;

    @Override
    public String format() {
        return String.format("%s [%s]: %s",
                getTimestamp(),
                severity.getUppercaseLevel(),
                message);
    }
}
