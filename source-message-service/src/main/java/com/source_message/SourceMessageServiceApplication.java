package com.source_message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.api.framework", "com.source_user_auth", "com.source_message"})
public class SourceMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceMessageServiceApplication.class, args);
    }

}
