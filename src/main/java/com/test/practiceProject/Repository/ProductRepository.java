package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 9:49 AM
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
