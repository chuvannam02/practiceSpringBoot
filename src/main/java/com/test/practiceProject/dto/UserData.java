package com.test.practiceProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:17 AM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private Long id;
    private String name;
    private String email;

    // getters/setters
}
