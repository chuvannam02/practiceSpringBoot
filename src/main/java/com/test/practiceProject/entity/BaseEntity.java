package com.test.practiceProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Thông tin metadata cơ bản chung cho tất cả entity")
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedBy
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(updatable = false)
    @Schema(description = "Người tạo", example = "admin")
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    @Schema(description = "Thời điểm tạo", example = "2025-10-04T14:48:00Z")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdDate;

    @LastModifiedBy
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @JsonIgnore
    @Schema(description = "Người chỉnh sửa gần nhất", example = "editor01")
    @Column(insertable = false)
    private String lastModifiedBy;

    @LastModifiedDate
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(insertable = false)
    @Schema(description = "Thời điểm chỉnh sửa gần nhất", example = "2025-10-04T15:10:00Z")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "dd-MM-yyyy hh")
    private Instant  lastModifiedDate;

//    dùng @Data (tự sinh equals()/hashCode()).

//    Chi tiết:

//    abstract class BaseEntity chứa các trường metadata (createdBy, createdDate, …).

//    Class con (Category, UserEntity, …) dùng @Data → Lombok sinh equals/hashCode cho các field trong class con.

//    Lombok cảnh báo "không gọi super.equals()", vì class con kế thừa từ BaseEntity, mà Lombok không biết bạn có muốn so sánh các trường của BaseEntity hay không.

//    Giải pháp khi dùng abstract BaseEntity:

//    Không tính field của BaseEntity trong equals/hashCode (thường là metadata không quan trọng để so sánh entity):

//    @Data
//    @EqualsAndHashCode(callSuper = false)
//    @Entity
//    public class Category extends BaseEntity {
        // ...
//    }


//    Nếu muốn tính cả field của BaseEntity:

//    @Data
//    @EqualsAndHashCode(callSuper = true)
//    @Entity
//    public class Category extends BaseEntity {
        // ...
//    }


//💡 Tip:

//    Với entity JPA, thường bạn chỉ muốn equals/hashCode dựa vào id, hoặc các field business chính.

//    Metadata như createdBy/createdDate không nên đưa vào equals/hashCode, nên callSuper = false là hợp lý.
}
