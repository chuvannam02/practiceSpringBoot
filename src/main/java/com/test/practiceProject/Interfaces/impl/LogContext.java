package com.test.practiceProject.Interfaces.impl;

import com.test.practiceProject.Interfaces.Loggable;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Interfaces.impl  *
 * @Author: ChuVanNam
 * @Date: 8/18/2025
 * @Time: 10:45 AM
 */

public class LogContext {
    private Loggable loggable;

    public LogContext(Loggable loggable) {
        this.loggable = loggable;
    }

    public LogContext() {
        // Default constructor
    }

    public void setLoggable(Loggable loggable) {
        this.loggable = loggable;
    }

    public void log() {
        if (loggable == null) {
            throw new IllegalStateException("No log strategy set");
        }
        System.out.println(loggable.format());
    }
}
