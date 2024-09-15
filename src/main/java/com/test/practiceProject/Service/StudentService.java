package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.CourseDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public StudentDTO getStudentById(int id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new BadRequestException("Student not found with ID: " + id);
        }

        StudentEntity student = studentOptional.get();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhone(student.getPhone());
        studentDTO.setAddress(student.getAddress());

        List<CourseDTO> courseDTOs = student.getCourses().stream().map(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setTitle(course.getTitle());
            return courseDTO;
        }).collect(Collectors.toList());

        studentDTO.setCourses(courseDTOs);

        return studentDTO;
    }

    @Transactional
    public void addCourseToStudent(int studentId, int courseId) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new BadRequestException("Student not found with ID: " + studentId);
        }

        Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new BadRequestException("Course not found with ID: " + courseId);
        }

        StudentEntity student = studentOptional.get();
        CourseEntity course = courseOptional.get();

        student.getCourses().add(course);
        studentRepository.saveAndFlush(student);
    }

    @Transactional
    public void deleteCourseFromStudent(int studentId, int courseId) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new BadRequestException("Student not found with ID: " + studentId);
        }

        Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new BadRequestException("Course not found with ID: " + courseId);
        }

        StudentEntity student = studentOptional.get();
        CourseEntity course = courseOptional.get();

        student.getCourses().remove(course);
        studentRepository.saveAndFlush(student);
    }
}
