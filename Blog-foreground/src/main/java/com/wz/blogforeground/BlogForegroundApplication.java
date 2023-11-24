package com.wz.blogforeground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = "com.wz")
@CrossOrigin
@ConfigurationPropertiesScan(value = "com.wz")
@EnableScheduling
public class BlogForegroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogForegroundApplication.class, args);
	}

}
