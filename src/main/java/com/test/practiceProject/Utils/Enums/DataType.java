package com.test.practiceProject.Utils.Enums;

import com.test.practiceProject.Error.BadRequestException;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Enums  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 1:56 AM
 */

public enum DataType {
    STRING("String"),
    INTEGER("Integer"),
    FLOAT("Float"),
    DOUBLE("Double"),
    BOOLEAN("Boolean"),
    DATE("Date"),
    TIME("Time"),
    DATETIME("DateTime"),
    LIST("List"),
    MAP("Map"),
    STRUCT("Struct");

    private final String typeName;

    DataType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static DataType fromString(String typeName) {
        for (DataType dataType : DataType.values()) {
            if (dataType.typeName.equalsIgnoreCase(typeName)) {
                return dataType;
            }
        }
        throw new BadRequestException("Unknown data type: " + typeName);
    }
}
