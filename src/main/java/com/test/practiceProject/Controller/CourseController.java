package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.CourseDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        BaseResponse baseResponse = new BaseResponse();
        courseService.addCourse(courseDTO);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Create new course successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCourseById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(courseService.getCourseById(id));
        baseResponse.setError_code("0");
        baseResponse.setMessage("Get course successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
