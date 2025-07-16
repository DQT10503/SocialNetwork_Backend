package com.source_content.service.impl;

import com.api.framework.utils.Constants;
import com.source_content.entity.TblPost;
import com.source_content.repository.TblPostRepository;
import com.source_content.service.EdgeRankScoringService;
import com.source_content.service.retrofit.RelationshipApiService;
import com.source_content.service.retrofit.UserApiService;
import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
    private final StringRedisTemplate stringRedisTemplate;

    public EdgeRankScoringServiceImpl(TblPostRepository postRepository, RelationshipApiService relationshipApiService, UserApiService userApiService, StringRedisTemplate stringRedisTemplate) {
        this.postRepository = postRepository;
        this.relationshipApiService = relationshipApiService;
        this.userApiService = userApiService;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public double calculateScore() {

        return 0;
    }

    private void calAffinity() {
        Map<Long, Set<Object>> mapFriendsWithUser = getFriendsByUserIds();
        Map<Long, Set<Long>> mapUserIdWithPostId = new HashMap<>();
        int page = 0;
        Page<TblPost> postPage;
        do {
            postPage = postRepository.findAllByStatusAndPrivacyLevelIsNot(ContentStatus.ACTIVE, PrivacyLevel.PRIVATE, PageRequest.of(page, Constants.PAGE_SIZE_DEFAULT));

            page++;
        } while (!postPage.isLast());

    }

    private Map<Long, Set<Object>> getFriendsByUserIds() {
        Map<Long, Set<Object>> result = new HashMap<>();
        Cursor<byte[]> cursor = Objects.requireNonNull(stringRedisTemplate.getConnectionFactory())
                .getConnection()
                .scan(ScanOptions.scanOptions().match("user:following:*").count(1000).build());

        while (cursor.hasNext()) {
            String key = new String(cursor.next());
            Set<Object> friends = Collections.singleton(stringRedisTemplate.opsForSet().members(key));
            Long userId = (new BigInteger(key.replace("user:following:", ""))).longValue();
            result.put(userId, friends);
        }
        return result;
    }

}

