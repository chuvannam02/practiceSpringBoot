package com.test.practiceProject.entity;

import com.test.practiceProject.utils.DataFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.entity  *
 * @Author: ChuVanNam
 * @Date: 10/7/2025
 * @Time: 11:11 PM
 */

//@Entity
//@Table(name = "role")
//public class Role extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String code;
//
//    private String name;
//
//    @PrePersist
//    @PreUpdate
//    public void preSave() {
//        if (DataFormat.trim(code) != null && (DataFormat.trim(code).startsWith("ROLE_"))) {
//            code = "ROLE_" + code.toUpperCase();
//        }
//    }
//}
