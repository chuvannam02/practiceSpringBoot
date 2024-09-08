package com.test.practiceProject.Repository;

import com.test.practiceProject.DTO.InstructorDTO;
import com.test.practiceProject.Entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {
    // that's it ... no need to write any code LOL!
    @Query(name = "SELECT * from instructor where id = ?1", nativeQuery = true)
    Optional<InstructorEntity> searchById(int id);
}
