package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.StudentDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        BaseResponse baseResponse = new BaseResponse();
        studentService.createNewStudent(studentDTO);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Create new student successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
