package com.test.practiceProject.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 9:32 AM
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "`product`")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "category")
    String category;

    @Column(name = "price")
    double price;

    @Column(name = "discount_percentage")
    double discountPercentage;

    @Column(name = "rating")
    double rating;

    @Column(name = "stock")
    int stock;

    @Column(name = "tags")
    String[] tags;

    @Column(name = "brand")
    String brand;

    @Column(name = "sku")
    String sku;

    @Column(name = "weight")
    int weight;

    @Lob
    @Column(name = "dimensions", columnDefinition = "TEXT")
    String dimensions;

    @Column(name = "warranty_information")
    String warrantyInformation;

    @Column(name = "shipping_information")
    String shippingInformation;

    @Column(name = "availability_status")
    String availabilityStatus;

    @Column(name = "return_policy")
    String returnPolicy;

    @Column(name = "minimum_order_quantity")
    int minimumOrderQuantity;

    @Lob
    @Column(name = "images", columnDefinition = "TEXT")
    String images; // Lưu trữ chuỗi JSON

    @Column(name = "thumbnail")
    String thumbnail;

    @Lob
    @Column(name = "meta", columnDefinition = "TEXT")
    String meta; // Lưu trữ chuỗi JSON

    @Lob
    @Column(name = "reviews", columnDefinition = "TEXT")
    String reviews; // Lưu trữ chuỗi JSON

}
