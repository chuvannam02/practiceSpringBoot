package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<LoginEntity, Integer> {
    // derived query methods
    LoginEntity findByUsername(String username);
    LoginEntity findById(int id);
}
