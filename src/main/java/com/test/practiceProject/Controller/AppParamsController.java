package com.test.practiceProject.Controller;

import com.test.practiceProject.Entity.AppParams;
import com.test.practiceProject.Service.AppParamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
}
