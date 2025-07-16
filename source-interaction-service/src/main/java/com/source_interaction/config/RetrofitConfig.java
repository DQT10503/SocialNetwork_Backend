package com.source_interaction.config;

import com.source_interaction.service.retrofit.PostApiService;
import com.source_interaction.service.retrofit.UserApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Value("${service-post.base-url}")
    private String baseUrlPost;
    @Value("${service-user.base-url}")
    private String baseUrlUser;

    @Bean
    public PostApiService configPostApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlPost)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        PostApiService postApiService = retrofit.create(PostApiService.class);
        return postApiService;
    }

    @Bean
    public UserApiService configUserApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlUser)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        UserApiService userApiService = retrofit.create(UserApiService.class);
        return userApiService;
    }
}
