package com.source_content.external.keycloak;

import com.source_content.external.keycloak.dto.TokenResponse;

import java.io.IOException;

public interface KeycloakClient {

    TokenResponse getTokenAdmin() throws IOException;
}
