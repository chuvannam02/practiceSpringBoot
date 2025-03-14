package com.test.practiceProject.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 10:58 AM
 */
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    int id;
    String title;
    String description;
    String category;
    double price;
    double discountPercentage;
    double rating;
    int stock;
    String[] tags;
    String brand;
    String sku;
    int weight;
}
