package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.utils.converter.StatusConverter;
import com.test.practiceProject.utils.enums.Status;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name="user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    Long id;

    String password;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    String email;

    @Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
    String username;

    String name;

    @ManyToOne(fetch = FetchType.LAZY) // ✅ nên dùng ManyToOne thay vì OneToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private CategoryItem gender; // 👈 đổi tên field cho dễ hiểu

    @Convert(converter = StatusConverter.class)
    Status status;

    @Column(name = "description", length = 500)
    String description;
}
