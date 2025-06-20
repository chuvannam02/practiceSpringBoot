package com.test.practiceProject.Entity.Angular;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
 * @Time: 4:43 PM
 */

@Entity
@Table(name = "role_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private Integer status;
    // Với status
    //    0. Đã xóa
    //    1. Hiển thị
    //    2. Không hiển thị
    private Integer idx;
}
