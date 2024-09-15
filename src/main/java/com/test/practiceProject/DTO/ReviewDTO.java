package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    private int id;
    private Integer rating;
    private String comment;
    private Integer courseId;
}
