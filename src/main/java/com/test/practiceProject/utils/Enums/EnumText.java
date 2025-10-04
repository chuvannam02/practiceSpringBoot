package com.test.practiceProject.utils.Enums;

public interface EnumText {
    String getText();

    String name();

    default Object additional() {
        return null;
    };
}

// Ctrl + Alt + L - format code intelij
