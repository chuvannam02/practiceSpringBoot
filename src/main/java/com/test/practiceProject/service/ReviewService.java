package com.test.practiceProject.service;

import com.test.practiceProject.dto.ReviewDTO;
import com.test.practiceProject.entity.CourseEntity;
import com.test.practiceProject.entity.ReviewEntity;
import com.test.practiceProject.error.BadRequestException;
import com.test.practiceProject.repository.CourseRepository;
import com.test.practiceProject.repository.ReviewRepository;
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
