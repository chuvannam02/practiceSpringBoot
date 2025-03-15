package com.test.practiceProject.Repository.Angular;

import com.test.practiceProject.Entity.Angular.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Repository.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:24 AM
 */

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
