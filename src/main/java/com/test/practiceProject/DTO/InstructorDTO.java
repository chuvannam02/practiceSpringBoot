package com.test.practiceProject.DTO;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InstructorDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String hobby;
    private String youtubeChannel;
}
