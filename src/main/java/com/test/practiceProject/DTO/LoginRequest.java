package com.test.practiceProject.DTO;

import com.test.practiceProject.Utils.Validations.PasswordConstrain;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequest {
    private String username;
    @PasswordConstrain
    private String password;
}
