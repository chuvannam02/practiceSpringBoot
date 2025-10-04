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
public class BaseEntity implements Serializable {

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

}
