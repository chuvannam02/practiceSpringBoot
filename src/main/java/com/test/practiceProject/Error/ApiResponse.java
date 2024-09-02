package com.test.practiceProject.Error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Data
public class ApiResponse {
    private final String code;
    private String message;
    private List<ObjectError> errors;

    private Map<String, String> additionalInformation;

    // Constructors, getters, and setters

    public ApiResponse(String code, String message, Map<String, String> additionalInformation) {
        this.code = code;
        this.message = message;
        this.additionalInformation = additionalInformation;
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, List<ObjectError> errors) {
        this.code = code;
        this.errors = errors;
    }
}
