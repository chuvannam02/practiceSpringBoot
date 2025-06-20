package com.test.practiceProject.DTO.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO.base  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 12:00 PM
 */

@Data
@SuperBuilder
public class SortDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private String propertySort;
}
