package com.test.practiceProject.Error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Data
public class ApiResponse {
    private final String code;
    private final String message;

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
}
