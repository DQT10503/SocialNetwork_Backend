package com.source_relationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.api.framework", "com.source_relationship"})
public class SourceRelationshipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceRelationshipServiceApplication.class, args);
    }

}
