package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.LoginRequest;
import com.test.practiceProject.DTO.RandomStuff;
import com.test.practiceProject.Response.AuthenticateResponse;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.AccountService;
import com.test.practiceProject.config.auth.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String jwtToken = "";
        Long expTime = (long) 24*60*60; // 1day
        Authentication authentication = accountService.authenticate(loginRequest);
        if (authentication.isAuthenticated()) {
            jwtToken = tokenProvider.generateToken(loginRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
        BaseResponse baseResponse = new BaseResponse();
        AuthenticateResponse authenticateResponse = new AuthenticateResponse();
        authenticateResponse.setExp(expTime);
        authenticateResponse.setAccess_token(jwtToken);
        baseResponse.setError_code("0");
        baseResponse.setObject(authenticateResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createNewAccount(@Valid @RequestBody LoginRequest info) {
        BaseResponse baseResponse = new BaseResponse();
        accountService.createUser(info);

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }
}
