package com.test.practiceProject.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`instructor`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorEntity {
    // annotate the class as an entity and map to db table
    // define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // Instructor-InstructorDetail One-To-One Unidirectional Relationship Mapping

    // By using CascadeType.ALL, it essentially means that any operation performed on the parent entity should also be performed on the child entity.
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     / With FetchType.EAGER, it will fetch the details of the Child along with the Parent i.e.
//    // both instructor and instructor details will be fetched in a single query.

    // With FetchType.LAZY and @ToString(exclude = "instructor_detail_id") at the entity Class level,
    // it will fetch the details of the Parent only i.e. instructor details will not be fetched in a single query.
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
