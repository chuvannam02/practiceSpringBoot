package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.AppParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppParamsRepository extends JpaRepository<AppParams, Long> {
}
