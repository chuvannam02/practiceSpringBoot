package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.ReviewDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createReview(@RequestBody ReviewDTO reviewDTO) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Review created successfully");
        baseResponse.setError_code("0");
        reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
