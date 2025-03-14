package com.test.practiceProject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 3/14/2025
 * @Time: 2:51 PM
 */

@RestController
@Slf4j
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String greeting(@RequestParam(defaultValue = "User") String name, Locale locale) {
        return messageSource.getMessage("hello", new Object[]{name}, locale);
    }
}
