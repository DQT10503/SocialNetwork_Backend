package com.source_user_auth.service.retrofit;

import com.source_user_auth.domain.keycloak.TokenResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

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

    @POST("/admin/realms/{realm}/users")
    Call<Void> createUser(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Body Map<String, Object> user
    );

    @PUT("/admin/realms/{realm}/users/{user_id}")
    Call<Void> updateUser(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Path("user_id") String userId,
            @Body Map<String, Object> user
    );

    @DELETE("/admin/realms/{realm}/users/{user_id}")
    Call<Void> deleteUser(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Path("user_id") String userId
    );

    @GET("/admin/realms/{realm}/users")
    Call<List<Map<String, Object>>> detailUser(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Query("username") String username
    );

    @GET("/admin/realms/{realm}/clients/{client_uuid}/roles/{role_name}")
    Call<Map<String, Object>> getClientRole(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Path("client_uuid") String clientUUID,
            @Path("role_name") String roleName
    );

    @POST("/admin/realms/{realm}/users/{user_id}/role-mappings/clients/{client_uuid}")
    Call<Void> assignRoleToUser(
            @Header("Authorization") String tokenAdmin,
            @Path("realm") String realm,
            @Path("user_id") String userId,
            @Path("client_uuid") String clientUUID,
            @Body List<Map<String, Object>> roles
    );

}
