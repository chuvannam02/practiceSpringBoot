package com.test.practiceProject.Records;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 9/30/2024
 * @Time 2:34 PM
 */
public record RecordPerson(String name, int age) {
    public RecordPerson {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
    }
}
