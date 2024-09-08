package com.test.practiceProject.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name="user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Mapping thông tin biến với tên cột trong Database
    @Column(name = "hp")
    int hp;
    @Column(name = "stamina")
    int stamina;

    // Nếu không đánh dấu @Column thì sẽ mapping tự động theo tên biến
    int atk;
    int def;
    int agi;
}
