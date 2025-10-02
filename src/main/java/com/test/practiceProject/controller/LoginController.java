package com.test.practiceProject.controller;

import com.test.practiceProject.dto.LoginRequest;
import com.test.practiceProject.dto.RandomStuff;
import com.test.practiceProject.dto.response.AuthenticateResponse;
import com.test.practiceProject.dto.response.BaseResponse;
import com.test.practiceProject.service.AccountService;
import com.test.practiceProject.config.auth.JwtTokenProvider;
import com.test.practiceProject.config.auth.SecurityContext;
import com.test.practiceProject.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        String jwtToken = "";
        Long expTime = (long) 24*60*60; // 1day

        // If client sent an existing access token, deny if it is blacklisted
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String pseudoJti = String.valueOf(tokenProvider.extractExpiration(token).getTime());
                if (refreshTokenService.isAccessTokenBlacklisted(pseudoJti)) {
                    BaseResponse forbidden = new BaseResponse();
                    forbidden.setError_code("403");
                    forbidden.setMessage("Token is blacklisted");
                    return new ResponseEntity<>(forbidden, HttpStatus.FORBIDDEN);
                }
            } catch (Exception ignored) {
                // If token parsing fails, proceed with normal login flow
            }
        }

        Authentication authentication = accountService.authenticate(loginRequest);
        if (authentication.isAuthenticated()) {
            jwtToken = tokenProvider.generateToken(loginRequest.getUsername());
            String refreshId = refreshTokenService.issueRefreshToken(loginRequest.getUsername());
            jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("refresh_token", refreshId);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(Math.toIntExact(14*24*60*60));
            response.addCookie(cookie);
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }

        BaseResponse baseResponse = new BaseResponse();
        SecurityContext.setCurrentToken(jwtToken);
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

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest req, HttpServletResponse res) {
        BaseResponse baseResponse = new BaseResponse();
        accountService.logout(req, res);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Logout success!");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse> refresh(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String accessToken = JwtTokenProvider.getTokenFromRequest(request);
        String oldJti = null;
        try {
            // reuse signature+issuedAt as a pseudo-jti (or embed jti later)
            oldJti = String.valueOf(tokenProvider.extractExpiration(accessToken).getTime());
        } catch (Exception ignored) {}

        String refreshId = null;
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie c : request.getCookies()) {
                if ("refresh_token".equals(c.getName())) {
                    refreshId = c.getValue();
                    break;
                }
            }
        }
        if (refreshId == null) {
            baseResponse.setError_code("401");
            baseResponse.setMessage("Missing refresh token");
            return new ResponseEntity<>(baseResponse, HttpStatus.UNAUTHORIZED);
        }

        String subject = refreshTokenService.resolveSubjectByRefreshToken(refreshId);
        if (subject == null) {
            baseResponse.setError_code("401");
            baseResponse.setMessage("Invalid refresh token");
            return new ResponseEntity<>(baseResponse, HttpStatus.UNAUTHORIZED);
        }

        String newAccess = tokenProvider.generateToken(subject);
        AuthenticateResponse authenticateResponse = new AuthenticateResponse();
        authenticateResponse.setAccess_token(newAccess);
        authenticateResponse.setExp(24*60*60L);
        baseResponse.setError_code("0");
        baseResponse.setObject(authenticateResponse);

        if (oldJti != null) {
            long remain = Math.max(1, (tokenProvider.extractExpiration(accessToken).getTime() - System.currentTimeMillis())/1000);
            refreshTokenService.blacklistAccessToken(oldJti, remain);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
