package com.source_content.external.keycloak;

import com.source_content.external.keycloak.dto.TokenResponse;
import com.source_content.service.retrofit.KeycloakApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

@Component
public class KeycloakClientImpl implements KeycloakClient {

    private final KeycloakApiService keycloakApiService;

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String resource;
    @Value("${keycloak-properties.client-id}")
    private String clientUUID;
    @Value("${keycloak-properties.client-secret}")
    private String clientSecret;
    @Value("${keycloak-properties.grant-type}")
    private String grantType;
    @Value("${keycloak-properties.username-admin}")
    private String usernameAdmin;
    @Value("${keycloak-properties.password-admin}")
    private String passwordAdmin;

    public KeycloakClientImpl(KeycloakApiService keycloakApiService) {
        this.keycloakApiService = keycloakApiService;
    }


    @Override
    public TokenResponse getTokenAdmin() throws IOException {
        Call<TokenResponse> call = keycloakApiService.getToken(realm, resource, grantType, clientSecret, usernameAdmin, passwordAdmin);
        Response<TokenResponse> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to get token: " + response.errorBody().string());
        }
        return response.body();
    }

}
