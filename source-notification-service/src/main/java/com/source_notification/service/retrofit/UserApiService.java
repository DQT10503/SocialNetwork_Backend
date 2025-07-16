package com.source_notification.service.retrofit;

import com.api.framework.domain.PagingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UserApiService {
    @GET("user")
    Call<PagingResponse> getUser(@Header("Authorization") String token, @Query("username") String username);
}
