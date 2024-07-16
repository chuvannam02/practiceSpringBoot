package com.test.practiceProject.Error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.Map;

@Getter
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, String> additionalInformation;

    private HttpStatus httpStatus;
    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(String message, HttpStatus httpStatus, Map<String, String> additionalInformation) {
        super(message);
        this.httpStatus = httpStatus;
        this.additionalInformation = additionalInformation;
    }

}
