package com.test.practiceProject.repository;

import com.test.practiceProject.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Repository  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 11:49 AM
 */

@Repository
public interface TableCustomRepository extends JpaRepository<Table, Integer> {
    // Custom query methods can be defined here if needed
    // For example, you can add methods to find tables by name, schema, etc.
    @Query("""
            SELECT t FROM table_custom t
            WHERE :searchKey IS NULL OR LOWER(t.name) ILIKE LOWER(CONCAT('%', :searchKey, '%'))
            ORDER BY greatest(t.createdDate, t.lastModifiedDate) DESC
            """)
    Page<Table> searchByName(@Param("searchKey") String searchKey, Pageable pageable);
}
