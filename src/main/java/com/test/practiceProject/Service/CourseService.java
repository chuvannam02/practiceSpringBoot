package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.CourseDTO;
import com.test.practiceProject.DTO.InstructorDTO;
import com.test.practiceProject.Entity.CourseEntity;
import com.test.practiceProject.Entity.InstructorEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.CourseRepository;
import com.test.practiceProject.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public void addCourse(CourseDTO courseDTO) {
        // Lấy InstructorEntity từ database dựa trên instructor_id có trong DTO
        Optional<InstructorEntity> instructorOptional = instructorRepository.findById(courseDTO.getInstructor_id());

        if (instructorOptional.isEmpty()) {
            throw new BadRequestException("Instructor không tồn tại với ID: " + courseDTO.getInstructor_id());
        }

        InstructorEntity instructor = instructorOptional.get();

        // Tạo CourseEntity và gán instructor đã có sẵn
        CourseEntity courseEntity = CourseEntity.builder()
                .title(courseDTO.getTitle())
                .instructor(instructor) // Gán instructor đã có từ database
                .build();

        // Lưu courseEntity vào repository
        courseRepository.saveAndFlush(courseEntity);
    }

    @Transactional
    public CourseDTO getCourseById(int id) {
        Optional<CourseEntity> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            throw new BadRequestException("Không tìm thấy khóa học với ID: " + id);
        }

        CourseEntity course = courseOptional.get();

        // Chuyển đổi thành DTO
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());

        // Chuyển đổi InstructorEntity thành InstructorDTO
        InstructorEntity instructor = course.getInstructor();
        if (instructor != null) {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setId(instructor.getId());
            instructorDTO.setEmail(instructor.getEmail());
            instructorDTO.setFirstName(instructor.getFirstName());
            instructorDTO.setLastName(instructor.getLastName());
            courseDTO.setInstructor(instructorDTO);
        }

        return courseDTO;
    }
}
