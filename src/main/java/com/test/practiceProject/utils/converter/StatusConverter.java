package com.test.practiceProject.utils.converter;

import com.test.practiceProject.utils.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.utils.converter  *
 * @Author: ChuVanNam
 * @Date: 10/7/2025
 * @Time: 11:41 PM
 */

//@Converter(autoApply = true) // Áp dụng tự động cho mọi field kiểu Status
//🧠 Ghi chú:
//    autoApply = true nghĩa là bạn không cần annotate @Convert ở entity.
//    Nếu bạn muốn chỉ áp dụng cho vài field, có thể bỏ autoApply và thêm @Convert(converter = StatusConverter.class) ở field cụ thể.
public class StatusConverter implements AttributeConverter<Status, Integer> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param status the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return (status != null) ? status.toValue() : null;
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        return Status.fromValue(dbData);
    }
}
