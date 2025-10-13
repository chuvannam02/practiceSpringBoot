package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.utils.converter.StatusConverter;
import com.test.practiceProject.utils.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.entity  *
 * @Author: ChuVanNam
 * @Date: 10/7/2025
 * @Time: 11:20 PM
 */

@Data
@Table(name = "category_item")
@Entity(name = "category_item")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CategoryItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

//    @Column(name = "category_id", nullable = false)
//    private Long category_id;

    // ✅ Một CategoryItem thuộc về một CategoryRepository
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category parentCategory;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    // Hibernate sẽ dùng StatusConverter tự động
    @Convert(converter = StatusConverter.class)
    private Status status;
}
