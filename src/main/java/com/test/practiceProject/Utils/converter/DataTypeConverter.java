package com.test.practiceProject.Utils.converter;

import com.test.practiceProject.Utils.Enums.DataType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.converter  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 12:15 PM
 */

@Converter(autoApply = true)
@Slf4j
public class DataTypeConverter implements AttributeConverter<DataType, String> {

    @Override
    public String convertToDatabaseColumn(DataType attribute) {
        if (attribute == null) {
            log.warn("DataType attribute is null. Returning null for database column.");
            return null;
        }
        return attribute == null ? null : attribute.name();
    }

    @Override
    public DataType convertToEntityAttribute(String dbData) {
        try {
            return DataType.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid DataType value: {}. Returning null.", dbData);
            // Có thể log cảnh báo và return null hoặc throw lỗi
            return null;
        }
    }
}
