package com.test.practiceProject.entity;

import com.test.practiceProject.utils.enums.DataType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 11:47 AM
 */

@Entity(name = "table_custom")
@jakarta.persistence.Table(name = "table_custom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Table extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String schemaName;
    private String tableType; // Ví dụ: "BASE TABLE", "VIEW"
//    @Enumerated(EnumType.STRING)
    private DataType dataType;
    private String primaryKey; // Tên của khóa chính
    private String foreignKey; // Tên của khóa ngoại, nếu có
    private String uniqueKey; // Tên của khóa duy nhất, nếu có
}
