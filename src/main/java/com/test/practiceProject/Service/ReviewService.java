package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.ReviewDTO;
import com.test.practiceProject.Entity.CourseEntity;
import com.test.practiceProject.Entity.ReviewEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.CourseRepository;
import com.test.practiceProject.Repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {
    ReviewRepository reviewRepository;
    CourseRepository courseRepository;

    public void createReview(ReviewDTO reviewDTO) {
        Optional<CourseEntity> course = courseRepository.findById(reviewDTO.getCourseId());
        if (course.isEmpty()) {
            // throw exception
            throw new BadRequestException("Course not found");
        }

        ReviewEntity reviewEntity = ReviewEntity.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .course(course.get())
                .build();

        reviewRepository.save(reviewEntity);
    }
}
