package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.InstructorDTO;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createNewInstructor(@RequestBody InstructorDTO instructorDTO) {
        BaseResponse baseResponse = new BaseResponse();
        instructorService.createNewInstructor(instructorDTO);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Create new instructor successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getInstructorById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setError_code("0");
        baseResponse.setObject(instructorService.getInstructorById(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteInstructorById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        instructorService.deleteInstructorById(id);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Delete instructor successfully !");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
