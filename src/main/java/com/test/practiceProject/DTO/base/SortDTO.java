package com.test.practiceProject.DTO.base;

import lombok.Builder;
import lombok.Data;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO.base  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 12:00 PM
 */

@Data
@Builder
public class SortDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private String propertySort;
}
