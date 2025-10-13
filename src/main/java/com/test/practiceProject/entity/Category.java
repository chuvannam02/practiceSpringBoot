package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.utils.converter.StatusConverter;
import com.test.practiceProject.utils.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.entity  *
 * @Author: ChuVanNam
 * @Date: 10/7/2025
 * @Time: 11:20 PM
 */

@Data
@Entity(name = "category")
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    // Hibernate sẽ dùng StatusConverter tự động
    @Convert(converter = StatusConverter.class)
    private Status status;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryItem> items;
}
