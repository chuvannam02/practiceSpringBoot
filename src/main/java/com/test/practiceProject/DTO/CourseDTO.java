package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.Entity.ReviewEntity;
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
}
