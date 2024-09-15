package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.StudentDTO;
import com.test.practiceProject.Entity.CourseEntity;
import com.test.practiceProject.Entity.StudentEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.CourseRepository;
import com.test.practiceProject.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {
    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Transactional
    public void createNewStudent(StudentDTO studentDTO) {
        Optional<CourseEntity> courseOptional = courseRepository.findById(studentDTO.getCourseId());
        if (courseOptional.isEmpty()) {
            throw new BadRequestException("Course not found with ID: " + studentDTO.getCourseId());
        }

        CourseEntity courseEntity = courseOptional.get();

        // create new student with initialized courses list
        StudentEntity studentEntity = StudentEntity.builder()
                .name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .phone(studentDTO.getPhone())
                .address(studentDTO.getAddress())
                .courses(new ArrayList<>()) // Initialize the courses list
                .build();

        // Add the course to the student's courses
        studentEntity.getCourses().add(courseEntity);

        // Save the student
        studentRepository.saveAndFlush(studentEntity);
    }
}
