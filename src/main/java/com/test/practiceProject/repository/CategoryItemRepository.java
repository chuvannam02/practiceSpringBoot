package com.test.practiceProject.repository;

import com.test.practiceProject.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.repository  *
 * @Author: ChuVanNam
 * @Date: 10/8/2025
 * @Time: 12:11 AM
 */

@Repository
public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(ci) > 0 THEN TRUE ELSE FALSE END
            FROM category_item ci
            WHERE ci.code = :code
                AND ((:categoryId IS NULL) OR (ci.parentCategory.id = :categoryId))
                AND ((:categoryItemId IS NULL) OR (ci.id <> :categoryItemId))
        """)
    boolean existsByCodeAndCategoryIdAndIdNotEqualCategoryItemId(String code, Long categoryId, Long categoryItemId);

//    💬 Vì sao không cần ON trong JOIN?
//    Vì JOIN ci.parentCategory c đã hiểu ngầm:
//        “join theo quan hệ được định nghĩa trong entity CategoryItem”.
//    Không cần viết ON c.id = ci.category_id như SQL truyền thống.
//    Đó là điểm khác biệt giữa JPQL và SQL.
    @Query("""
            SELECT CASE WHEN COUNT(ci) > 0 THEN TRUE ELSE FALSE END
            FROM category_item ci
            JOIN ci.parentCategory c
            WHERE ci.code = :code
                AND ((:categoryCode IS NULL) OR (c.code = :categoryCode))
                AND ((:categoryItemId IS NULL) OR (ci.id <> :categoryItemId))
        """)
    boolean existsByCodeAndCategoryCodeAndIdNotEqualCategoryItemId(String code, String categoryCode, Long categoryItemId);
}
