package com.test.practiceProject.repository;

import com.test.practiceProject.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<LoginEntity, Integer> {
    // derived query methods
    LoginEntity findByUsername(String username);
    LoginEntity findById(int id);
}
