package com.test.practiceProject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String token;

    public LoginResponse(String jwt) {
        this.token = jwt;
    }
}
