package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.AppParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppParamsRepository extends JpaRepository<AppParams, Long> {
    Optional<AppParams> findByParamName(String paramName);
}
