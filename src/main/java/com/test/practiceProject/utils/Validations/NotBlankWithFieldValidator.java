package com.test.practiceProject.utils.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Validations  *
 * @Author: ChuVanNam
 * @Date: 3/14/2025
 * @Time: 2:07 PM
 */

public class NotBlankWithFieldValidator implements ConstraintValidator<NotBlankWithField, String> {

    private String fieldLabel;

    @Override
    public void initialize(NotBlankWithField constraintAnnotation) {
        this.fieldLabel = constraintAnnotation.fieldLabel();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{NotBlank.field}")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

