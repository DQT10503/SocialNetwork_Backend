package com.source_interaction.service.retrofit;

import com.api.framework.domain.PagingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PostApiService {
    @GET("post")
    Call<PagingResponse> getPost(@Header("Authorization") String token, @Query("id") Long id);
}
