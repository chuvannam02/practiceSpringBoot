package com.test.practiceProject.repository;

import com.test.practiceProject.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {
    // that's it ... no need to write any code LOL!
}
