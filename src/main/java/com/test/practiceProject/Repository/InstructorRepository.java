package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {
    // that's it ... no need to write any code LOL!

}
