package com.source_content.service.retrofit;

import com.source_content.external.keycloak.dto.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface KeycloakApiService {

    @FormUrlEncoded
    @POST("/realms/{realm}/protocol/openid-connect/token")
    Call<TokenResponse> getToken(
            @Path("realm") String realm,
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("client_secret") String clientSecret,
            @Field("username") String username,
            @Field("password") String password
    );
}
