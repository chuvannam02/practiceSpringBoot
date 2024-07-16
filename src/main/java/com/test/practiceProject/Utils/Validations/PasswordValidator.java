package com.test.practiceProject.Utils.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstrain, String> {
    @Override
    public void initialize(PasswordConstrain password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        int length = password.length();
        if (length < 8 || length > 14) {
            return false;
        }

//        if (!password.matches(".*\\d.*")) {
//            return false;
//        }
//
//        if (!password.matches(".*[A-Z].*")) {
//            return false;
//        }
//
//        if (!password.matches(".*[a-z].*")) {
//            return false;
//        }

        return true;
    }
}
