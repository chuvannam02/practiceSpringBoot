package com.test.practiceProject.dto;

import com.test.practiceProject.utils.Validations.PasswordConstrain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Schema(description = "Thông tin người dùng")
public class LoginRequest {

    @Schema(description = "Tên đăng nhập", example = "namcv")
    private String username;

    @PasswordConstrain
    @Schema(description = "Mật khẩu", example = "abc@abcA")
    private String password;
}
