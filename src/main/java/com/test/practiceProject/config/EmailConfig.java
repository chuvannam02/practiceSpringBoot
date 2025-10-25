package com.test.practiceProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config  *
 * @Author: ChuVanNam
 * @Date: 10/25/2025
 * @Time: 1:36 AM
 */
// Thymeleaf config
@Configuration
public class EmailConfig {

//    @Bean
//    public ISpringTemplateEngine emailTemplateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new Java8TimeDialect());
//        engine.addTemplateResolver(textTemplateResolver());
//        engine.addTemplateResolver(htmlTemplateResolver());
//        return engine;
//    }
//
//    @Bean
//    public ITemplateResolver htmlTemplateResolver() {
//        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix("email-templates/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setOrder(2);
//        return resolver;
//    }
//
//    @Bean
//    public ITemplateResolver textTemplateResolver() {
//        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix("email-templates/");
//        resolver.setSuffix(".txt");
//        resolver.setTemplateMode(TemplateMode.TEXT);
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setOrder(1);
//        return resolver;
//    }

    @Bean
    public TemplateEngine emailTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("email-templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
