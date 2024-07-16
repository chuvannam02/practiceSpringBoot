package com.test.practiceProject.config.type;

import lombok.Getter;

@Getter
public enum BookType implements EnumText{
    FICTION("book_type.fiction"),
    MYSTERY("book_type.mystery"),
    HISTORY("book_type.history"),
    SHORT_STORIES("book_type.short_stories")
    ;

    private final String text;
    BookType(String text) {
        this.text = text;
    }
}
