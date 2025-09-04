package com.source_interaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);                    // số luồng tối thiểu chạy luôn
        executor.setMaxPoolSize(10);                    // số luồng tối đa
        executor.setQueueCapacity(50);                  // số task chờ queue
        executor.setThreadNamePrefix("AsyncThread-");   // để log dễ đọc
        executor.initialize();                          // phải init
        return executor;
    }
}
