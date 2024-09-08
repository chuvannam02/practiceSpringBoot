package com.test.practiceProject.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.config.type.BookType;
import jakarta.persistence.*;
import lombok.*;
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
public class BookEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2936687026040726549L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;
    String name;
    String description;
    transient int copies;

    // Trạng thái sách:  - 0: Đang được mượn - 1: Có thể mượn
    int status;

    @Enumerated(EnumType.STRING)
    BookType bookType;
}
