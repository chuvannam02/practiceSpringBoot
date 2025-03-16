package com.test.practiceProject.Entity.Angular;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/16/2025
 * @Time: 4:45 PM
 */

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    // Với status
    //    0. Đã xóa
    //    1. Hiển thị
    //    2. Không hiển thị
}
