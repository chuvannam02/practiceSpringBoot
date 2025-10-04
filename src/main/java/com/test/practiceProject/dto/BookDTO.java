package com.test.practiceProject.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO đại diện cho thông tin sách")
public class BookDTO {

    @Schema(description = "ID của sách", example = "101")
    int bookId;

    @Schema(description = "Tên sách", example = "Lập trình Java nâng cao")
    String name;

    @Schema(description = "Mô tả nội dung sách", example = "Cuốn sách giới thiệu chi tiết về Java Spring Boot")
    String description;

    @Schema(description = "Số lượng bản sao có sẵn", example = "5")
    int copies;

    @Schema(
        description = "Thể loại sách, ánh xạ với Enum BookType",
        example = "FICTION"
    )
    int bookType;

    @Schema(
        description = "Trạng thái sách (0: INACTIVE, 1: ACTIVE)",
        example = "1"
    )
    int status;
}

