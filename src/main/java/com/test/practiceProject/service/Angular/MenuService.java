package com.test.practiceProject.service.Angular;

import com.test.practiceProject.dto.in.MenuDTO;
import com.test.practiceProject.entity.Angular.Menu;
import com.test.practiceProject.repository.Angular.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:06 AM
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public void saveMenu(MenuDTO dto) {
        log.info("Save menu: {}", dto);
        if (dto.getId() == null) {
            create(dto);
        } else {
            update(dto);
        }
    }

    private void create(MenuDTO dto) {

    }

    private void update(MenuDTO dto) {

    }

    public Page<Menu> findAll(MenuDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());
        return menuRepository.findAll(pageable);
    }
}
