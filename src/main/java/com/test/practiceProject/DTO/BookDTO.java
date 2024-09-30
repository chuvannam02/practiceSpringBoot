package com.test.practiceProject.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {
    int bookId;
    // Alt + Shift + 2 phim mũi tên xuống, lên để chọn nhiều dòng
    // hoặc Alt + Shift + Click chuột trái để chọn nhiều dòng
    String name;
    String description;
    int copies;

    int bookType;
    int status;
}
