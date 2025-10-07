package com.test.practiceProject.utils.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Utils.Validations  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:29 AM
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = MaxLengthWithFieldValidator.class)
public @interface MaxLengthWithField {
//    message: thông báo lỗi (có thể lấy từ ValidationMessages.properties).
    String message() default "{MaxLength.field}";

//              groups: dùng để phân nhóm validation.
//              payload được dùng để gắn thêm thông tin tùy ý vào một constraint để sau này có thể xử lý khi validation bị lỗi.
//            👉 Ví dụ: Bạn muốn biết lỗi validation nào là “nghiêm trọng”, hoặc ai là người tạo ra constraint này, bạn có thể truyền payload.
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
    String fieldLabel();

//    max: giá trị tối đa của chuỗi.
    int max();
}
