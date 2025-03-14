package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.Utils.Validations.NotBlankWithField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
//@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
    @NotBlankWithField(fieldLabel = "tên sinh viên")
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer courseId;
    private List<CourseDTO> courses;
}
