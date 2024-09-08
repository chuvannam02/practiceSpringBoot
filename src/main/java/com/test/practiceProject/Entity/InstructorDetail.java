package com.test.practiceProject.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`instructor_detail`")
public class InstructorDetail {
    // annotate the class as an entity and map to db table
    // define the fields
    // annotate the fields with db column names

    // define primary key
    @Id
    // configure the primary key to auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // map the field to db column
    @Column(name = "id")
    private int id;
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Column(name = "hobby")
    private String hobby;

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
