package com.test.practiceProject.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "app_params")
// Bạn phải đánh dấu class bởi @EntityListeners(AuditingEntityListener.class)
// Đây là Một đối tượng Listener, lắng nghe sự kiện insert hoặc update của đối tượng
// Để từ đó tự động cập nhật các trường @CreatedDate và @LastModifiedDate
@EntityListeners(AuditingEntityListener.class)
public class AppParams implements Serializable {
    @Serial
    private static final long serialVersionUID = 2783421558989997612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String paramName;
    private String paramValue;

    /*
    đánh dấu trường Date với @CreatedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào createdAt
     */
    @CreatedDate
    private Date createdAt;

    /*
    đánh dấu trường Date với @LastModifiedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào updatedAt
    Nếu đối tượng được update vào database lần tiếp theo -> nó sẽ tự động lấy thời điểm đó cập nhật vào updatedAt
     */
    @LastModifiedDate
    private Date updatedAt;
}
