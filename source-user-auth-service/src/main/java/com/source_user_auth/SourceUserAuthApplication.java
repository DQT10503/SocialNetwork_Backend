package com.source_user_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.api.framework", "com.source_user_auth"})
public class SourceUserAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourceUserAuthApplication.class);
    }
}