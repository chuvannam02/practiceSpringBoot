package com.test.practiceProject.dto.in;

import com.test.practiceProject.entity.BaseEntity;
import lombok.Data;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO.in  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 11:53 AM
 */

@Data
public class TableCustomDTO extends BaseEntity {
    private String name;
    private String description;
    private String schemaName;
    private String tableType; // Ví dụ: "BASE TABLE", "VIEW"
    private String dataType; // Ví dụ: "String", "Integer", "Date", v.v.
    private String primaryKey; // Tên của khóa chính
    private String foreignKey; // Tên của khóa ngoại, nếu có
    private String uniqueKey; // Tên của khóa duy nhất, nếu có
}
