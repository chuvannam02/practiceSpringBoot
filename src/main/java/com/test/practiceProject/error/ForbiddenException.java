package com.test.practiceProject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.error  *
 * @Author: ChuVanNam
 * @Date: 10/3/2025
 * @Time: 1:46 AM
 */


@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
