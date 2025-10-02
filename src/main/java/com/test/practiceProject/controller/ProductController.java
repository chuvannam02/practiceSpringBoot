package com.test.practiceProject.controller;

import com.test.practiceProject.dto.response.BaseResponse;
import com.test.practiceProject.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 9:57 AM
 */
@RestController
@RequestMapping("/v1/product")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<BaseResponse> getProduct() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Product fetched successfully");
        baseResponse.setObject(productService.getProduct());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<BaseResponse> getTest() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Test fetched successfully");
        productService.test();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
