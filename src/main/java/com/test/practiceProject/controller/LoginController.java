package com.test.practiceProject.controller;

import com.test.practiceProject.dto.LoginRequest;
import com.test.practiceProject.dto.RandomStuff;
import com.test.practiceProject.dto.response.AuthenticateResponse;
import com.test.practiceProject.dto.response.BaseResponse;
import com.test.practiceProject.service.AccountService;
import com.test.practiceProject.config.auth.JwtTokenProvider;
import com.test.practiceProject.config.auth.SecurityContext;
import com.test.practiceProject.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Login API", description = "Quản lý đăng nhập, đăng ký tài khoản, đăng xuất")
public class LoginController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Operation(
        summary = "Đăng nhập hệ thống",
        description = "Xác thực username/password và trả về Access Token + Refresh Token"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Đăng nhập thành công"),
        @ApiResponse(responseCode = "401", description = "Sai thông tin đăng nhập hoặc token không hợp lệ"),
        @ApiResponse(responseCode = "403", description = "Token bị blacklist")
    })
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Thông tin đăng nhập gồm username và password",
            required = true
        )
        @Valid @RequestBody LoginRequest loginRequest,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        String jwtToken = "";
        Long expTime = (long) 24 * 60 * 60; // 1day
//
//        // If client sent an existing access token, deny if it is blacklisted
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            try {
//                String pseudoJti = String.valueOf(tokenProvider.extractExpiration(token).getTime());
//                if (refreshTokenService.isAccessTokenBlacklisted(pseudoJti)) {
//                    BaseResponse forbidden = new BaseResponse();
//                    forbidden.setError_code("403");
//                    forbidden.setMessage("Token is blacklisted");
//                    return new ResponseEntity<>(forbidden, HttpStatus.FORBIDDEN);
//                }
//            } catch (Exception ignored) {
//                // If token parsing fails, proceed with normal login flow
//            }
//        }

        Authentication authentication = accountService.authenticate(loginRequest);
        if (authentication.isAuthenticated()) {
            jwtToken = tokenProvider.generateToken(loginRequest.getUsername());
            String refreshId = refreshTokenService.issueRefreshToken(loginRequest.getUsername());
            jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("refresh_token", refreshId);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(Math.toIntExact(14 * 24 * 60 * 60));
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

    @Operation(summary = "API test JWT", description = "Chỉ truy cập được khi có JWT hợp lệ")
    @ApiResponse(responseCode = "200", description = "Trả về random message")
    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff() {
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

    @Operation(summary = "Tạo tài khoản mới", description = "Đăng ký tài khoản mới vào hệ thống")
    @ApiResponse(responseCode = "200", description = "Tạo thành công")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createNewAccount(@Valid @RequestBody LoginRequest info) {
        BaseResponse baseResponse = new BaseResponse();
        accountService.createUser(info);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @Operation(summary = "Đăng xuất", description = "Xóa token, hủy phiên đăng nhập")
    @ApiResponse(responseCode = "200", description = "Đăng xuất thành công")
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest req, HttpServletResponse res) {
        BaseResponse baseResponse = new BaseResponse();
        accountService.logout(req, res);
        baseResponse.setError_code("0");
        baseResponse.setMessage("Logout success!");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @Operation(
        summary ="Làm mới Access Token",
        description ="Dùng refresh token (lưu trong cookie) để lấy access token mới"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Làm mới token thành công"),
        @ApiResponse(responseCode = "401", description = "Refresh token không hợp lệ hoặc thiếu")
    })
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse> refresh(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String accessToken = JwtTokenProvider.getTokenFromRequest(request);
        String oldJti = null;
        try {
            // reuse signature+issuedAt as a pseudo-jti (or embed jti later)
            oldJti = String.valueOf(tokenProvider.extractExpiration(accessToken).getTime());
        } catch (Exception ignored) {
        }

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
        authenticateResponse.setExp(24 * 60 * 60L);
        baseResponse.setError_code("0");
        baseResponse.setObject(authenticateResponse);

        if (oldJti != null) {
            long remain = Math.max(1, (tokenProvider.extractExpiration(accessToken).getTime() - System.currentTimeMillis()) / 1000);
            refreshTokenService.blacklistAccessToken(oldJti, remain);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
