package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.utils.enums.BookType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
//Comparator<BookEntity>
public class BookEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2936687026040726549L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID sách", example = "1")
    int bookId;

    @Schema(description = "Tên sách", example = "Spring Boot in Action")
    String name;

    @Schema(description = "Mô tả sách", example = "Cuốn sách hướng dẫn học Spring Boot")
    String description;

    @Transient
    @Schema(description = "Số lượng bản sao (chỉ dùng tạm, không lưu DB)", example = "10")
//    transient int copies;
    int copies;

    // Trạng thái sách:  - 0: Đang được mượn - 1: Có thể mượ'/n
    @Schema(description = "Trạng thái sách: 0 - Đang mượn, 1 - Có thể mượn", example = "1")
    int status;

    //    @Schema(description = "Thể loại sách", example = "NOVEL")
//    @Schema(
//        description = "Thể loại sách",
//        example = "FICTION",
//        allowableValues = {"FICTION", "MYSTERY", "HISTORY", "SHORT_STORIES"}
//    )
    @Schema(
        description = "Thể loại sách. Bao gồm: " +
            "FICTION = Tiểu thuyết hư cấu, " +
            "MYSTERY = Truyện trinh thám, " +
            "HISTORY = Sách lịch sử, " +
            "SHORT_STORIES = Tuyển tập truyện ngắn",
        example = "MYSTERY",
        allowableValues = {"FICTION", "MYSTERY", "HISTORY", "SHORT_STORIES"},
        implementation = BookType.class
    )
    @Enumerated(EnumType.STRING)
    BookType bookType;
//    https://gpcoder.com/2610-treeset-va-su-dung-comparable-comparator-trong-java/
//    https://www.geeksforgeeks.org/treeset-in-java-with-examples/
//    @Override
//    public int compare(BookEntity o1, BookEntity o2) {
//        return o1.getName().compareTo(o2.getName());
//    }
}
