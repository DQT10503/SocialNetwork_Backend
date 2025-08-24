package com.source_content.service.retrofit;

import com.api.framework.domain.PagingResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface    UserApiService {

    @GET("user")
    Call<PagingResponse> getUser(@Header("Authorization") String token, @Query("username") String username);
}
