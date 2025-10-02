package com.test.practiceProject.dto;

import com.test.practiceProject.utils.Validations.PasswordConstrain;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequest {
    private String username;
    @PasswordConstrain
    private String password;
}
