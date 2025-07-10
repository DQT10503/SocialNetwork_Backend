package com.source_interaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.api.framework","com.source_user_auth","com.source_interaction"})
public class SourceInteractionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceInteractionServiceApplication.class, args);
    }

}
