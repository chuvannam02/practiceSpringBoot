package com.test.practiceProject.controller;

import com.test.practiceProject.entity.AppParams;
import com.test.practiceProject.dto.request.AppParamsRequest;
import com.test.practiceProject.dto.response.BaseResponse;
import com.test.practiceProject.service.AppParamsService;
import com.test.practiceProject.utils.enums.DayOfWeek;
import com.test.practiceProject.utils.enums.Season;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("audit")
public class AppParamsController {
    private final Logger log = LoggerFactory.getLogger(AppParamsController.class);
    @Autowired
    private AppParamsService appParamsService;

    @PutMapping("/update/{id}")
    public AppParams updateAppParams(@PathVariable("id") Long id, @RequestParam String param_name, @RequestParam String param_value) {
        return appParamsService.update(id, param_name, param_value);
    }

    @GetMapping("test")
    public String test() {
        return "Test";
    }

    @GetMapping("get-all")
    public List<AppParams> getAll() {
        return appParamsService.getAll();
    }

    @PostMapping("/test-post/{id}")
    public String testPost(@PathVariable Long id) {
        return "OK";
    }

    @PostMapping("/create-new")
    public ResponseEntity<BaseResponse> create(@RequestBody AppParamsRequest params) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(appParamsService.createNew(params));
//        appParamsService.createNew(params);
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-season")
    public ResponseEntity<BaseResponse> getSeason() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(appParamsService.getCharacteristicOfSeason(Season.SUMMER));
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-day-type")
    public ResponseEntity<BaseResponse> getDayType() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(appParamsService.getDayOfType(DayOfWeek.MONDAY));
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/test-record")
    public ResponseEntity<BaseResponse> testRecord() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(appParamsService.createRecordPerson("Chunam", 25));
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/test1")
    public ResponseEntity<BaseResponse> test1() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(appParamsService.test());
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }
}
