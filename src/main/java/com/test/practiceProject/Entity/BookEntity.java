package com.test.practiceProject.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.config.type.BookType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
public class BookEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2936687026040726549L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String name;
    private String description;
    private transient int copies;

    @Enumerated(EnumType.STRING)
    private BookType bookType;
}
