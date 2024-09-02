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
            context.buildConstraintViolationWithTemplate("Mật khẩu không được để trống!")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        int length = password.length();
        if (length < 8 || length > 14) {
            context.buildConstraintViolationWithTemplate("Mật khẩu phải có độ dài từ 8 đến 14 ký tự.")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        // Uncomment these lines if you want to include these checks
        if (!password.matches(".*\\d.*")) {
            context.buildConstraintViolationWithTemplate("Mật khẩu phải chứa ít nhất một chữ số.")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            context.buildConstraintViolationWithTemplate("Mật khẩu phải chứa ít nhất một chữ cái viết hoa.")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            context.buildConstraintViolationWithTemplate("Mật khẩu phải chứa ít nhất một chữ cái viết thường.")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }
}
