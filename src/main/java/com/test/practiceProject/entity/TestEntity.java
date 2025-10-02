package com.test.practiceProject.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 9/17/2024
 * @Time 5:18 PM
 */
@Builder
@Getter
@Setter
@Table(name = "test_entity")
@Entity
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class TestEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "status")
    String status;
}
