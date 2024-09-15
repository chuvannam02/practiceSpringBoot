package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private Integer  id;
    private String title;
    private Integer  instructor_id;
    private InstructorDTO instructor;
}
