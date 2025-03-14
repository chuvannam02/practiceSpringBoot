package com.test.practiceProject.config.auth;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 2:34 PM
 */
public class SecurityContext {
    private static final ThreadLocal<String> currentToken = new ThreadLocal<>();

    public static void setCurrentToken(String token) {
        currentToken.set(token);
    }

    public static String getCurrentToken() {
        return currentToken.get();
    }

    public static void clear() {
        currentToken.remove();
    }
}
