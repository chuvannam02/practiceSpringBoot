package com.test.practiceProject.config.auth;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
//http://localhost:8080/swagger-ui.html hoặc /swagger-ui/index.html
public class ApiSecurityConfig {
    /**
     * @return
     * termsOfService: link chính sách sử dụng API
     * server environments: dev, staging, production
     * tags: phân loại API theo module (auth, user, order, …)
     */
//    Phím tắt tạo JavaDoc
//    Đặt con trỏ ngay trên class, method hoặc field → nhấn:
//    Alt + Enter → chọn "Add Javadoc"** hoặc **/ + Enter`** ngay trên dòng khai báo.
//👉 IntelliJ sẽ tự động sinh ra template JavaDoc.
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
            .name("Authorization")
            .description("Enter JWT token with **Bearer** prefix, e.g. 'Bearer eyJhbGciOi...'")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT");
    }


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info()
                .title("My REST API")
                .description("Some custom description of API.")
                .version("1.0")
                .termsOfService("https://mycompany.com/terms")
                .contact(new Contact()
                    .name("Chu Văn Nam")
                    .email("namcv12@gmail.com")
                    .url("https://mycompany.com"))
                .license(new License().name("License of API").url("https://mycompany.com/license")))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("Development Server"),
                new Server().url("http://localhost:8080").description("Staging Server"),
                new Server().url("http://localhost:8080").description("Production Server")
            ));
    }

//    Springdoc hỗ trợ export OpenAPI YAML/JSON:
//        → http://localhost:8080/v3/api-docs (JSON)
//        → http://localhost:8080/v3/api-docs.yaml (YAML)

//    Bạn nên gắn thêm các annotation mô tả API bằng Swagger / OpenAPI (springdoc-openapi) để tài liệu tự sinh ra trong Swagger UI.
//    Ví dụ bạn có thể dùng các annotation sau:
//    @Operation → mô tả API, summary + description
//    @ApiResponses / @ApiResponse → mô tả response (200, 401, 403, …)
//    @Parameter → mô tả request param / header
//    @RequestBody → mô tả request body
}
