package com.test.practiceProject.Repository.Angular;

import com.test.practiceProject.Entity.Angular.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.management.Query;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Repository.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 12:27 PM
 */

@Repository
public class CustomMenuRepositoryImpl implements CustomMenuRepository{
    /**
     * @param code
     * @param name
     * @param parentId
     * @param appId
     * @param pageable
     * @return
     */
    @Override
    public Page<Menu> findAllMenuyByPage(String code, String name, Integer parentId, Integer appId, Pageable pageable) {
        return null;
    }
}
