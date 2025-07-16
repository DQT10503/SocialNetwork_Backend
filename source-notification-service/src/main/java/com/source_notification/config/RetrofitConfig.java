package com.source_notification.config;

import com.source_notification.service.retrofit.UserApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Value("${service-user.base-url}")
    private String baseUrlUser;

    @Bean
    public UserApiService configUserApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlUser)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(UserApiService.class);
    }
}
