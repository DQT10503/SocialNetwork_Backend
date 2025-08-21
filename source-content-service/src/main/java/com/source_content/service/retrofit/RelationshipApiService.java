package com.source_content.service.retrofit;

import com.api.framework.domain.PagingResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface RelationshipApiService {

    @GET("follower")
    Call<PagingResponse> getFollower(
            @Header("Authorization") String token,
            @Query("followerId") Long followerId,
            @Query("offset") int offset);

}
