package com.test.practiceProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.practiceProject.Utils.Validations.PasswordConstrain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "`account`")
@Data // lombok
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @NotNull
    @Size(min = 3)
    @Column(nullable = false, unique = true, columnDefinition = "varchar(255) COLLATE utf8mb4_unicode_ci")
    String username;

    @JsonIgnore
    @NotNull
    @PasswordConstrain
    String password;

    Integer numberFailures;
    String role;
}
