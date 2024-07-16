package com.test.practiceProject.Utils.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstrain {
    String message() default "Mật khẩu không hợp lệ!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
