package com.example.MyMarket.domain.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/oauth/token")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST","GET");
        registry.addMapping("/api/v1/user")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST");
        registry.addMapping("/api/v1/parqueTecnologico")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST","GET");
        registry.addMapping("/api/v1/setor")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST","GET","DELETE");
        registry.addMapping("/api/v1/setor/*")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST","GET","DELETE");
        registry.addMapping("/api/v1/*")
                .allowedOrigins("http://localhost:9000")
                .allowedMethods("POST","GET","DELETE");
    }
}