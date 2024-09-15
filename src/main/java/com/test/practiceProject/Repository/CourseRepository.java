package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    // that's it ... no need to write any code LOL!
    List<CourseEntity> findByInstructorId(int instructorId);
}
