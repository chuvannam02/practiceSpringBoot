package com.test.practiceProject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity  *
 * @Author: Admin
 * @Date: 1/12/2025
 * @Time: 11:54 AM
 */

@Data
@AllArgsConstructor
@ToString
public class Person implements Serializable {
    private String name;
    private int age;
}
