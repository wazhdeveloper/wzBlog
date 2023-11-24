package com.wz.blogbackstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(scanBasePackages = "com.wz")
public class BlogBackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogBackstageApplication.class, args);
    }
}
