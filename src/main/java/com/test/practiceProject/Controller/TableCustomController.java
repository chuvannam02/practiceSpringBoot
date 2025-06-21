package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.in.TableCustomDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.TableCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 11:50 AM
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/table-custom")
public class TableCustomController {
        private final TableCustomService tableCustomService;

        @PostMapping("/save")
        public ResponseEntity<BaseResponse> saveTableCustom(@RequestBody TableCustomDTO tableCustomDTO) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Table custom saved successfully");
            tableCustomService.save(tableCustomDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

        @GetMapping("/get-all")
        public ResponseEntity<BaseResponse> getAllTableCustom(@RequestParam(required = true, defaultValue = "1") Integer page,
                                                              @RequestParam(required = true, defaultValue = "10") Integer size,
                                                              @RequestParam(required = false, defaultValue = "") String searchKey) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get all table custom successfully");
            baseResponse.setObject(tableCustomService.getAll(page, size, searchKey));
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
}
