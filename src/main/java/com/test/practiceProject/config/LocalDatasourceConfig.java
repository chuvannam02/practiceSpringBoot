package com.test.practiceProject.config;

import com.test.practiceProject.Request.LocalDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Chỉ khi profiles là "local"
 * Thì Configuration dưới đây mới được khởi tạo
 */
@Configuration
@Profile("local")
public class LocalDatasourceConfig {

    @Bean
    public LocalDatasource localDatasource() {
        return new LocalDatasource("Local object, Chỉ khởi tạo khi 'local' profile active");
    }
}
