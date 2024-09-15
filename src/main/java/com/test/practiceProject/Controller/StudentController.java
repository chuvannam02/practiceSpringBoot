package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.StudentDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(studentService.getStudentById(id));
        baseResponse.setError_code("0");
        baseResponse.setMessage("Get student by ID successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/add-course/{studentId}/{courseId}")
    public ResponseEntity<BaseResponse> addCourseToStudent(@PathVariable int studentId, @PathVariable int courseId) {
        BaseResponse baseResponse = new BaseResponse();
        studentService.addCourseToStudent(studentId, courseId);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Add course to student successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-course/{studentId}/{courseId}")
    public ResponseEntity<BaseResponse> deleteCourseFromStudent(@PathVariable int studentId, @PathVariable int courseId) {
        BaseResponse baseResponse = new BaseResponse();
        studentService.deleteCourseFromStudent(studentId, courseId);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Delete course from student successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
