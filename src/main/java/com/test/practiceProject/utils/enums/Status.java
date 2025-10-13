package com.test.practiceProject.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.utils.Enums  *
 * @Author: ChuVanNam
 * @Date: 10/7/2025
 * @Time: 11:22 PM
 */

@AllArgsConstructor
@Getter
public enum Status {
    ACTIVE(1, "Đang hoạt động"),
    INACTIVE(0, "Dừng hoạt động");
    private int value;
    private String description;

    public static Status fromValue(Integer intStatus) {
        if (intStatus == null) return null;
        for (Status status : values()) {
            if (status.value == intStatus) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + intStatus);
    }

    public static Status fromValueStream(Integer intStatus) {
        return Arrays.stream(values())
            .filter(s -> s.value == intStatus)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid status value: " + intStatus));
    }

    public int toValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("%s(%d - %s)", name(), value, description);
    }
}
