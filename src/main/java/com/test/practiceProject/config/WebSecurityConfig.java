package com.test.practiceProject.config;

import com.test.practiceProject.config.auth.AuditorAwareImpl;
import com.test.practiceProject.config.auth.JwtAuthenticationFilter;
import com.test.practiceProject.config.auth.UserInfoUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.SecureRandom;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class WebSecurityConfig {
    @Autowired
    private JwtAuthenticationFilter authFilter;

    private final String[] AUTH_WHITELIST = {
            "/api-docs/**",
            "/swagger-ui/**",
            "/api/create",
            "/api/login",
            "/swagger-ui.html",
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return new AuditorAwareImpl();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable() // Disable CSRF protection for simplicity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(AUTH_WHITELIST).permitAll() // Whitelist requests
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // Add custom filter
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint((request, response, exception) -> {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
                            response.getWriter().write("Access Denied!");
                        })
                )
                .authenticationProvider(authenticationProvider()); // Add custom authentication provider

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5, new SecureRandom());
    }
}