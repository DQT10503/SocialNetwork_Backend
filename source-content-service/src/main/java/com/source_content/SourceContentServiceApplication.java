package com.source_content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@ComponentScan(basePackages = {"com.api.framework", "com.source_content"})
public class SourceContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceContentServiceApplication.class, args);
    }

}
