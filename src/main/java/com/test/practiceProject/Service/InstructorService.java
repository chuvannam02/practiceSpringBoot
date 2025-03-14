package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.InstructorDTO;
import com.test.practiceProject.Entity.CourseEntity;
import com.test.practiceProject.Entity.InstructorDetail;
import com.test.practiceProject.Entity.InstructorEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.CourseRepository;
import com.test.practiceProject.Repository.InstructorDetailRepository;
import com.test.practiceProject.Repository.InstructorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InstructorService {
    InstructorRepository instructorRepository;
    InstructorDetailRepository instructorDetailRepository;
    CourseRepository courseRepository;

//    To Roll back checked Exceptions, we need to specify property rollbackFor
//    @Transactional(rollbackFor = SQLException.class)
    @Transactional
    public void createNewInstructor(InstructorDTO instructorDTO) throws Exception {
        try {
            // create a new instructor detail
            InstructorDetail instructorDetail = InstructorDetail.builder()
                    .hobby(instructorDTO.getHobby())
                    .youtubeChannel(instructorDTO.getYoutubeChannel())
                    .build();

            System.out.println(instructorDTO);
            // create a new instructor
            InstructorEntity instructor = InstructorEntity.builder()
                    .firstName(instructorDTO.getFirstName())
                    .lastName(instructorDTO.getLastName())
                    .email(instructorDTO.getEmail())
                    .instructorDetail(instructorDetail)
                    .build();

            // save the instructor (instructorDetail will be saved automatically due to CascadeType.ALL)
            instructorRepository.save(instructor);
//        throw new RuntimeException();

//        Checked Exception will not rolled back even though we have specified Transactional annotation
//            throw new SQLException();

            throw new RuntimeException();
        } catch (Exception ex) {
            System.out.println("Here we catch the exception.");
        }
    }

    public void updateInstructor(InstructorDTO instructorDTO) {
        InstructorEntity instructor = instructorRepository.findById(instructorDTO.getId()).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (instructor != null) throw new BadRequestException("Không tìm thấy thông tin người hướng dẫn!", status);
    }

    @Transactional
    public InstructorEntity getInstructorById(int id) {
        Optional<InstructorEntity> instructor = instructorRepository.searchById(id);
        if (instructor.isPresent()) {
            InstructorEntity instructorEntity = instructor.get();

            // Tải danh sách courses từ courseRepository
            List<CourseEntity> courses = courseRepository.findByInstructorId(id);
            // Đảm bảo rằng danh sách courses đã được nạp
            instructorEntity.setCourses(courses);

            return instructorEntity;
        } else {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Không tìm thấy thông tin hướng dẫn!", status);
        }
    }

    public void deleteInstructorById(int id) {
        instructorRepository.deleteById(id);
    }
}
