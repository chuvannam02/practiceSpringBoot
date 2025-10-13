package com.test.practiceProject.repository;

import com.test.practiceProject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.repository  *
 * @Author: ChuVanNam
 * @Date: 10/8/2025
 * @Time: 12:10 AM
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END
            FROM category c
            WHERE c.code = :code
                AND ((:categoryId IS NULL) OR (c.id <> :categoryId)) 
        """)
    boolean existsByCodeAndIdIsNot(String code, Long categoryId);
}
