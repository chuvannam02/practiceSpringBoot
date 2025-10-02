package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.config.type.BookType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    int bookId;
    String name;
    String description;
    transient int copies;

    // Trạng thái sách:  - 0: Đang được mượn - 1: Có thể mượ'/n
    int status;

    @Enumerated(EnumType.STRING)
    BookType bookType;
//    https://gpcoder.com/2610-treeset-va-su-dung-comparable-comparator-trong-java/
//    https://www.geeksforgeeks.org/treeset-in-java-with-examples/
//    @Override
//    public int compare(BookEntity o1, BookEntity o2) {
//        return o1.getName().compareTo(o2.getName());
//    }
}
