package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer courseId;
}
