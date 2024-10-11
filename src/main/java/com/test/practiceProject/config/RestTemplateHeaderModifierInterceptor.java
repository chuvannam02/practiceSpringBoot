package com.test.practiceProject.config;

import com.test.practiceProject.config.auth.JwtTokenProvider;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/3/2024
 * @Time 10:06 PM
 */
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateHeaderModifierInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Authentication: {}", authentication);
        log.info("Response: {}", response);
        log.info("Request: {}", request);
        log.info("Token: {}", authentication.getCredentials());
        if (authentication == null) {
            return response;
        }
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//        if (jwt == null) {
//            return response;
//        }
//        response.getHeaders().add("Authorization", "Bearer " + jwt.toString());
        return response;
    }
}
