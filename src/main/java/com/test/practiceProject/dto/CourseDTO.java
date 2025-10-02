package com.test.practiceProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private Integer  id;
    private String title;
    private Integer  instructor_id;
    private InstructorDTO instructor;
    private List<ReviewDTO> reviews;
    private List<StudentDTO> students;
}
