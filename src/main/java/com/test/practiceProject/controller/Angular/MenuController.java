package com.test.practiceProject.controller.Angular;

import com.test.practiceProject.dto.in.MenuDTO;
import com.test.practiceProject.utils.Validations.groups.Create;
import com.test.practiceProject.utils.Validations.groups.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 10:59 AM
 */

@RestController
@RequestMapping("/v1/menu")
public class MenuController {
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Validated(Create.class) MenuDTO dto) {
        // validate theo group Create
        return ResponseEntity.ok("Đã tạo!");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Validated(Update.class) MenuDTO dto) {
        // validate theo group Update
        return ResponseEntity.ok("Đã cập nhật!");
    }
}
