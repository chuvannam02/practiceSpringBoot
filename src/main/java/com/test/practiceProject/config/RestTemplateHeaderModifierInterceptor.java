package com.test.practiceProject.config;

import com.test.practiceProject.config.auth.JwtTokenProvider;
import com.test.practiceProject.config.auth.SecurityContext;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static com.test.practiceProject.config.auth.JwtTokenProvider.getTokenFromRequest;

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
        // Get the token from the SecurityContext (ThreadLocal)
        String token = SecurityContext.getCurrentToken();
        log.info("Token from SecurityContext: " + token);

//        if (StringUtils.hasText(token)) {
//            request.getHeaders().add("Authorization", "Bearer " + token);
//            log.info("Added token to request: " + token);
//        }

        return execution.execute(request, body);
    }
}