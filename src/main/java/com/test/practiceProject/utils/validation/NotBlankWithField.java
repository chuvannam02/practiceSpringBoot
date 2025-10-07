package com.test.practiceProject.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Validations  *
 * @Author: ChuVanNam
 * @Date: 3/14/2025
 * @Time: 2:06 PM
 */

// Annotation @Documented giúp việc sử dụng annotation này sẽ được ghi chú vào tài liệu JavaDoc
@Documented
// Annotation @Constraint định nghĩa rằng annotation này sẽ được sử dụng như một annotation hợp lệ trong việc kiểm tra dữ liệu
@Constraint(validatedBy = NotBlankWithFieldValidator.class)
// Annotation @Target định nghĩa rằng annotation này sẽ được sử dụng trên các trường, phương thức, tham số, hoặc annotation khác
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
// Annotation @Retention định nghĩa rằng annotation này sẽ được giữ lại ở mức thời gian chạy
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankWithField {
    // Các thuộc tính của annotation
    String message() default "{NotBlank.field}"; // message trong ValidationMessages.properties

    // groups và payload là các thuộc tính bắt buộc phải có trong mỗi annotation
    // chúng dùng để xác định nhóm các ràng buộc và thông tin bổ sung
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldLabel();
}