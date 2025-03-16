package com.test.practiceProject.Repository.Angular;

import com.test.practiceProject.Entity.Angular.Role;
import com.test.practiceProject.Entity.Angular.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Repository.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/16/2025
 * @Time: 4:45 PM
 */

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {
    List<RoleMenu> findByRole(Role role);
}
