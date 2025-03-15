package com.test.practiceProject.Repository.Angular;

import com.test.practiceProject.Entity.Angular.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Repository.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 12:25 PM
 */

public interface CustomMenuRepository {
    Page<Menu> findAllMenuyByPage(String code, String name, Integer parentId, Integer appId, Pageable pageable);
}
