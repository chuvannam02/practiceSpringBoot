package com.test.practiceProject.repository;

import com.test.practiceProject.entity.ProductEntity;
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
