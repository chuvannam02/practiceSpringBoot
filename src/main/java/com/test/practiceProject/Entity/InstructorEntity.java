package com.test.practiceProject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "`instructor`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InstructorEntity {
    // annotate the class as an entity and map to db table
    // define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="email")
    String email;

    // Instructor-InstructorDetail One-To-One Unidirectional Relationship Mapping

    // By using CascadeType.ALL, it essentially means that any operation performed on the parent entity should also be performed on the child entity.
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     / With FetchType.EAGER, it will fetch the details of the Child along with the Parent i.e.
//    // both instructor and instructor details will be fetched in a single query.

    // With FetchType.LAZY and @ToString(exclude = "instructor_detail_id") at the entity Class level,
    // it will fetch the details of the Parent only i.e. instructor details will not be fetched in a single query.
    @JoinColumn(name = "instructor_detail_id")
    InstructorDetail instructorDetail;

    // Instructor-Course One-To-Many Bidirectional Relationship Mapping
    @OneToMany(mappedBy = "instructor", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CourseEntity> courses;

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
