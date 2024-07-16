package com.test.practiceProject.Controller;

import com.test.practiceProject.Entity.UserEntity;
import com.test.practiceProject.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<UserEntity> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("username", "Jovan");

        //add cookie to response
        response.addCookie(cookie);

        return "Username is changed!";
    }
}
