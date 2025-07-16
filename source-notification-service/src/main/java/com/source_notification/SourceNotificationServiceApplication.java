package com.source_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.api.framework","com.source_notification"})
public class SourceNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceNotificationServiceApplication.class, args);
    }

}
