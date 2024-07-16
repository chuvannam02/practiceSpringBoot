package com.test.practiceProject.Error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_EXISTED("User created", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
