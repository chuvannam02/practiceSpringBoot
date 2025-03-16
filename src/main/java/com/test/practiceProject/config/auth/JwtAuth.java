package com.test.practiceProject.config.auth;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config.auth  *
 * @Author: ChuVanNam
 * @Date: 3/16/2025
 * @Time: 4:43 PM
 */

@Getter
@AllArgsConstructor
@ToString
public class JwtAuth {
    private final String token;
    private final String username;
    private final List<String> authorities;
    private final Claims claims;
}
