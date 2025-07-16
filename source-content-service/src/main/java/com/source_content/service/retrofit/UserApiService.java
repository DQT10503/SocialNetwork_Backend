package com.source_content.service.retrofit;

import com.api.framework.domain.PagingResponse;
import com.source_user_auth.domain.keycloak.TokenResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApiService {

    @GET("user")
    Call<PagingResponse> getUser(@Header("Authorization") String token, @Query("username") String username);

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
