package com.source_content.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.utils.Constants;
import com.api.framework.utils.Utilities;
import com.source_content.domain.follower.FollowerResponse;
import com.source_content.domain.user.UserResponse;
import com.source_content.entity.TblPost;
import com.source_content.repository.TblPostRepository;
import com.source_content.service.EdgeRankScoringService;
import com.source_content.service.retrofit.RelationshipApiService;
import com.source_content.service.retrofit.UserApiService;
import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;
import com.source_user_auth.domain.keycloak.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class EdgeRankScoringServiceImpl implements EdgeRankScoringService {

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String resource;
    @Value("${keycloak-properties.client-secret}")
    private String clientSecret;
    @Value("${keycloak-properties.grant-type}")
    private String grantType;
    @Value("${keycloak-properties.username-admin}")
    private String usernameAdmin;
    @Value("${keycloak-properties.password-admin}")
    private String passwordAdmin;

    private final TblPostRepository postRepository;
    private final RelationshipApiService relationshipApiService;
    private final UserApiService userApiService;

    public EdgeRankScoringServiceImpl(TblPostRepository postRepository, RelationshipApiService relationshipApiService, UserApiService userApiService) {
        this.postRepository = postRepository;
        this.relationshipApiService = relationshipApiService;
        this.userApiService = userApiService;
    }


    @Override
    public double calculateScore() {

        return 0;
    }

    private void a() {
        Map<Long, Set<Long>> friendMap =
        Map<Long, Set<Long>> mapUserIdWithPostId = new HashMap<>();
        int page = 0;
        Page<TblPost> postPage;
        do {
            postPage = postRepository.findAllByStatusAndPrivacyLevelIsNot(ContentStatus.ACTIVE, PrivacyLevel.PRIVATE, PageRequest.of(page, Constants.PAGE_SIZE_DEFAULT));

            page++;
        } while (!postPage.isLast());

    }

    private void mapFriend(String token, List<Long> followerIds) throws IOException {
        Map<Long, Set<Long>> friendMap = new HashMap<>();
        for (Long followerId : followerIds) {
            int page = 0;
            Set<Long> friendIds = new HashSet<>();
            while (true) {
                Call<PagingResponse> call = relationshipApiService.getFollower(token, followerId, page);
                Response<PagingResponse> response = call.execute();
                List<PagingResponse> pagingResponse = (List<PagingResponse>) response.body().getData();
                List<FollowerResponse> lsFollower = Utilities.copyProperties(pagingResponse, FollowerResponse.class);
                if (response.body().getData().isEmpty()) {
                    break;
                }
                if (Objects.isNull(lsFollower) || lsFollower.isEmpty()) {
                    break;
                }
                page++;
            }
            friendMap.put(followerId, friendIds);
        }

    }

    private String getTokenAdmin() throws IOException {
        Call<TokenResponse> call = userApiService.getToken(realm, resource, grantType, clientSecret, usernameAdmin, passwordAdmin);
        Response<TokenResponse> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to get token: " + response.errorBody().string());
        }
        return response.body().getAccessToken();
    }
}

