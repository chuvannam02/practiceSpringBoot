package com.test.practiceProject.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String hobby;
    private String youtubeChannel;
}
