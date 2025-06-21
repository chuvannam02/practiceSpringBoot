package com.test.practiceProject.Utils.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Validations  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:30 AM
 */

public class MaxLengthWithFieldValidator implements ConstraintValidator<MaxLengthWithField, String> {

    private int max;
    private String fieldLabel;

    @Override
    public void initialize(MaxLengthWithField constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.fieldLabel = constraintAnnotation.fieldLabel();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // Không kiểm tra null ở đây, để @NotNull xử lý nếu cần

        if (value.length() > max) {
            context.disableDefaultConstraintViolation();

            // Tạo thông báo động từ template
            String errorMessage = fieldLabel + " không được vượt quá " + max + " ký tự.";

            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
