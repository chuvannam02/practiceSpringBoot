package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.practiceProject.config.type.BookType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BookDTO {
    private int bookId;
    private String name;
    private String description;
    private int copies;

    private int bookType;
    private int status;
}
