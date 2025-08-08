package com.source_interaction.service.retrofit;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_interaction.domain.post.PostResponse;
import com.source_interaction.domain.user.UserResponse;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

@Service
public class CallApiExternalService {
    private final PostApiService postApiService;
    private final UserApiService userApiService;
    private final MessageUtil messageUtil;

    public CallApiExternalService(PostApiService postApiService, UserApiService userApiService, MessageUtil messageUtil) {
        this.postApiService = postApiService;
        this.userApiService = userApiService;
        this.messageUtil = messageUtil;
    }

    @SuppressWarnings("unchecked")
    public PostResponse getPostById(Long postId) {
        try {
            Call<PagingResponse> call = postApiService.getPost("Bearer " + BearerContextHolder.getContext().getToken(), postId);
            Response<PagingResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Post API call failed: " + response.code() + " - " + response.message());
            }
            List<PagingResponse> pagingRs = (List<PagingResponse>) response.body().getData();
            List<PostResponse> posts = Utilities.copyProperties(pagingRs, PostResponse.class);
            return posts.get(0);
        } catch (Exception e) {
            throw new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "Post ID: " + postId);
        }
    }

    @SuppressWarnings("unchecked")
    public UserResponse getUser() {
        try {
            Call<PagingResponse> call = userApiService.getUser("Bearer " + BearerContextHolder.getContext().getToken(), BearerContextHolder.getContext().getMasterAccount());
            Response<PagingResponse> response = call.execute();
            if(!response.isSuccessful()) {
                throw new RuntimeException("User API call failed: " + response.code() + " - " + response.message());
            }
            List<PagingResponse> pagingRs = (List<PagingResponse>) response.body().getData();
            List<UserResponse> users = Utilities.copyProperties(pagingRs, UserResponse.class);
            return users.get(0);
        } catch (Exception e) {
            throw new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "Username: " + BearerContextHolder.getContext().getMasterAccount());
        }
    }
}
