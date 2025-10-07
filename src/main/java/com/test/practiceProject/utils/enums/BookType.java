package com.test.practiceProject.utils.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Thể loại sách")
public enum BookType implements EnumText {

    @Schema(description = "Tiểu thuyết hư cấu")
    FICTION("book_type.fiction"),

    @Schema(description = "Truyện trinh thám, bí ẩn")
    MYSTERY("book_type.mystery"),

    @Schema(description = "Sách lịch sử")
    HISTORY("book_type.history"),

    @Schema(description = "Tuyển tập truyện ngắn")
    SHORT_STORIES("book_type.short_stories");

    private final String text;

    BookType(String text) {
        this.text = text;
    }
}
