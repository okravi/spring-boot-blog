package com.springboot.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Blog REST APIs",
                "Spring Boot Bloag REST API Documentation",
                "1",
                "Terms of service",
                new Contact("John Salindger", "johnsalindger.io", "john@salindger.com"),
                "LIcense of API",
                "License URL", 
                Collections.emptyList());               
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();               
    }
}
