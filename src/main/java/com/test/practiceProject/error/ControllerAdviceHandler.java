package com.test.practiceProject.error;

import com.test.practiceProject.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Error  *
 * @Author: Chu Văn Nam
 * @Date: 3/14/2025
 * @Time: 10:41 AM
 */

@ControllerAdvice
public class ControllerAdviceHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        BaseResponse<Map<String, String>> response = new BaseResponse<>();
        response.setError_code("400");
        response.setMessage("Validation failed");
        response.setData(null);
        response.setObject(errors); // gom lỗi vào object

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<String>> handleBadRequestException(BadRequestException ex) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setError_code(ex.getHttpStatus() != null ? ex.getHttpStatus().toString() : "400");
        response.setMessage(ex.getMessage() != null ? ex.getMessage() : "Bad request");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
