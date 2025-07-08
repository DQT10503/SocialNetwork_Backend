package com.source_user_auth.config;

import com.source_user_auth.service.retrofit.KeycloakApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfitAuthService {

    @Value("${keycloak.auth-server-url}")
    private String basuUrlKeycloak;

    @Bean
    public KeycloakApiService configKeycloakApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basuUrlKeycloak)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(KeycloakApiService.class);
    }
}
