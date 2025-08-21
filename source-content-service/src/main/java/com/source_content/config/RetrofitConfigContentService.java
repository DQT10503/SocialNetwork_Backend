package com.source_content.config;

import com.source_content.service.retrofit.KeycloakApiService;
import com.source_content.service.retrofit.RelationshipApiService;
import com.source_content.service.retrofit.UserApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfigContentService {
    @Value("${service.user.base-url}")
    private String baseUrlUser;
    @Value("${service.relationship.base-url}")
    private String baseUrlRelationship;
    @Value("${service.interaction.base-url}")
    private String baseUrlInteraction;
    @Value("${keycloak.auth-server-url}")
    private String baseUrlKeycloak;

    @Bean
    public UserApiService configUserApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlUser)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        UserApiService userApiService = retrofit.create(UserApiService.class);
        return userApiService;
    }

    @Bean
    public RelationshipApiService configRelationshipApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlRelationship)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        RelationshipApiService relationshipApiService = retrofit.create(RelationshipApiService.class);
        return  relationshipApiService;
    }

    @Bean
    public KeycloakApiService configKeycloakApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlKeycloak)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        KeycloakApiService keycloakApiService = retrofit.create(KeycloakApiService.class);
        return keycloakApiService;
    }

}
