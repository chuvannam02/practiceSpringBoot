package com.test.practiceProject.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config  *
 * @Author: Chu Văn Nam
 * @Date: 3/14/2025
 * @Time: 11:03 AM
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Trường hợp Token hết hạn hoặc không hợp lệ
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"message\": \"Token đã hết hạn hoặc không hợp lệ!\"}");
    }
}
