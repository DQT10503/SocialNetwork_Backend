package com.source_content.service.impl;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.Constants;
import com.api.framework.utils.DateTimeUtils;
import com.source_content.domain.interaction_service.BaseInteractionRequest;
import com.source_content.domain.interaction_service.comment.CommentRequest;
import com.source_content.domain.interaction_service.comment.CommentResponse;
import com.source_content.domain.interaction_service.like.LikeRequest;
import com.source_content.domain.interaction_service.like.LikeResponse;
import com.source_content.domain.interaction_service.share.ShareRequest;
import com.source_content.domain.interaction_service.share.ShareResponse;
import com.source_content.external.feign_client.InteractionFeignClient;
import com.source_content.external.keycloak.KeycloakClient;
import com.source_content.repository.TblPostRepository;
import com.source_content.service.EdgeRankScoringService;
import com.source_content.service.retrofit.RelationshipApiService;
import com.source_content.service.retrofit.UserApiService;
import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PointActionType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiFunction;

@Transactional
@Service
public class EdgeRankScoringServiceImpl implements EdgeRankScoringService {

    private final TblPostRepository postRepository;
    private final RelationshipApiService relationshipApiService;
    private final UserApiService userApiService;
    private final StringRedisTemplate stringRedisTemplate;
    private final InteractionFeignClient interactionFeignClient;
    private final KeycloakClient keycloakClient;

    public EdgeRankScoringServiceImpl(TblPostRepository postRepository, RelationshipApiService relationshipApiService, UserApiService userApiService, StringRedisTemplate stringRedisTemplate, InteractionFeignClient interactionFeignClient, KeycloakClient keycloakClient) {
        this.postRepository = postRepository;
        this.relationshipApiService = relationshipApiService;
        this.userApiService = userApiService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.interactionFeignClient = interactionFeignClient;
        this.keycloakClient = keycloakClient;
    }


    @Override
    public Map<Long, Map<Long, Double>> calculateScore() throws IOException {
        String tokenAdmin = keycloakClient.getTokenAdmin().getAccessToken();
        BearerContextHolder.getContext().setToken(tokenAdmin);
        Map<Long, Map<Long, Integer>> affinityMap = calAffinity();
        Map<Long, Map<Long, Double>> decayMap = calDecay();
        Map<Long, Map<Long, Double>> finalScoreMap = new HashMap<>();

        for (Map.Entry<Long, Map<Long, Integer>> entry : affinityMap.entrySet()) {
            Long user1 = entry.getKey();
            Map<Long, Integer> user1Affinity = entry.getValue();

            Map<Long, Double> user1FinalScore = new HashMap<>();
            for (Map.Entry<Long, Integer> inner : user1Affinity.entrySet()) {
                Long user2 = inner.getKey();
                Integer affinity = inner.getValue();
                Double decay = decayMap.getOrDefault(user1, Collections.emptyMap()).get(user2);

                if (decay != null) {
                    user1FinalScore.put(user2, affinity * decay);
                }
            }
            finalScoreMap.put(user1, user1FinalScore);
        }

        return finalScoreMap;
    }

    private Map<Long, Map<Long, Integer>> calAffinity() {
        InteractionData interactionData = fetchDataInteractions();
        List<LikeResponse> likeResponses = interactionData.getLikes();
        List<CommentResponse> commentResponses = interactionData.getComments();
        List<ShareResponse> shareResponses = interactionData.getShares();
        Map<Long, Map<Long, Integer>> affinityMap = new HashMap<>();

        for (LikeResponse like : likeResponses) {
            if (like.getUserId().equals(like.getAuthorId())) {
                continue;
            }
            Map<Long, Integer> affinityValue = affinityMap.computeIfAbsent(like.getUserId(), k -> new HashMap<>());
            affinityValue.put(like.getAuthorId(), affinityValue.getOrDefault(like.getAuthorId(), 0) + PointActionType.LIKE.getValue());
            affinityMap.put(like.getUserId(), affinityValue);
        }
        for (CommentResponse comment : commentResponses) {
            if (comment.getUserId().equals(comment.getAuthorId())) {
                continue;
            }
            Map<Long, Integer> affinityValue = affinityMap.computeIfAbsent(comment.getUserId(), k -> new HashMap<>());
            affinityValue.put(comment.getAuthorId(), affinityValue.getOrDefault(comment.getAuthorId(), 0) + PointActionType.COMMENT.getValue());
            affinityMap.put(comment.getUserId(), affinityValue);
        }
        for (ShareResponse share : shareResponses) {
            if (share.getUserId().equals(share.getAuthorId())) {
                continue;
            }
            Map<Long, Integer> affinityValue = affinityMap.computeIfAbsent(share.getUserId(), k -> new HashMap<>());
            affinityValue.put(share.getAuthorId(), affinityValue.getOrDefault(share.getAuthorId(), 0) + PointActionType.SHARE.getValue());
            affinityMap.put(share.getUserId(), affinityValue);
        }

        return affinityMap;
    }

    private Map<Long, Map<Long, Double>> calDecay() {
        InteractionData interactionData = fetchDataInteractions();
        List<LikeResponse> likeResponses = interactionData.getLikes();
        List<CommentResponse> commentResponses = interactionData.getComments();
        List<ShareResponse> shareResponses = interactionData.getShares();

        Map<Long, Map<Long, Instant>> mapUserIdWithAuthorIdAndValue = new HashMap<>();
        Map<Long, Instant> mapAuthorWithCreatedAt = new HashMap<>();
        for (LikeResponse like : likeResponses) {
            mapAuthorWithCreatedAt.put(like.getAuthorId(), like.getCreatedAt());
            if (mapUserIdWithAuthorIdAndValue.isEmpty()) {
                mapUserIdWithAuthorIdAndValue.put(like.getUserId(), mapAuthorWithCreatedAt);
                continue;
            }
            Instant temp = mapUserIdWithAuthorIdAndValue.get(like.getUserId()).get(like.getAuthorId());
            mapUserIdWithAuthorIdAndValue.forEach((key, value) -> {
                if (Objects.equals(like.getUserId(), key) && DateTimeUtils.isBeforeDate(temp, like.getCreatedAt())) {
                    mapUserIdWithAuthorIdAndValue.put(key, mapAuthorWithCreatedAt);
                }
            });
        }
        for (CommentResponse cmt : commentResponses) {
            mapAuthorWithCreatedAt.put(cmt.getAuthorId(), cmt.getCreatedAt());
            if (mapUserIdWithAuthorIdAndValue.isEmpty()) {
                mapUserIdWithAuthorIdAndValue.put(cmt.getUserId(), mapAuthorWithCreatedAt);
                continue;
            }
            Instant temp = mapUserIdWithAuthorIdAndValue.get(cmt.getUserId()).get(cmt.getAuthorId());
            mapUserIdWithAuthorIdAndValue.forEach((key, value) -> {
                if (Objects.equals(cmt.getUserId(), key) && DateTimeUtils.isBeforeDate(temp, cmt.getCreatedAt())) {
                    mapUserIdWithAuthorIdAndValue.put(key, mapAuthorWithCreatedAt);
                }
            });
        }
        for (ShareResponse share : shareResponses) {
            mapAuthorWithCreatedAt.put(share.getAuthorId(), share.getCreatedAt());
            if (mapUserIdWithAuthorIdAndValue.isEmpty()) {
                mapUserIdWithAuthorIdAndValue.put(share.getUserId(), mapAuthorWithCreatedAt);
                continue;
            }
            Instant temp = mapUserIdWithAuthorIdAndValue.get(share.getUserId()).get(share.getAuthorId());
            mapUserIdWithAuthorIdAndValue.forEach((key, value) -> {
                if (Objects.equals(share.getUserId(), key) && DateTimeUtils.isBeforeDate(temp, share.getCreatedAt())) {
                    mapUserIdWithAuthorIdAndValue.put(key, mapAuthorWithCreatedAt);
                }
            });
        }

        Map<Long, Map<Long, Double>> decayMap = new HashMap<>();
        Map<Long, Double> decayValue = new HashMap<>();
        LocalDate today = LocalDate.from(DateTimeUtils.getCurrentTimeUTC().atZone(ZoneId.of("UTC")));
        double lambda = 0.1;
        mapAuthorWithCreatedAt.forEach((k, v) -> {
            mapUserIdWithAuthorIdAndValue.forEach((key, value) -> {
                int timePass = (int) ChronoUnit.DAYS.between(LocalDate.from(value.get(k)), today);
                Double decay = Math.pow(Math.E, -lambda * timePass);
                decayValue.put(k, decay);
                decayMap.put(key, decayValue);
            });
        });

        return decayMap;
    }

    private InteractionData fetchDataInteractions() {
        BaseInteractionRequest likeRequest = new LikeRequest(ContentStatus.ACTIVE);
        BaseInteractionRequest commentRequest = new CommentRequest(ContentStatus.ACTIVE);
        BaseInteractionRequest shareRequest = new ShareRequest(ContentStatus.ACTIVE);

        List<LikeResponse> likes = getPagedData(likeRequest, interactionFeignClient::getLikes, LikeResponse.class);
        List<CommentResponse> comments = getPagedData(commentRequest, interactionFeignClient::getComments, CommentResponse.class);
        List<ShareResponse> shares = getPagedData(shareRequest, interactionFeignClient::getShares, ShareResponse.class);

        return new InteractionData(likes, comments, shares);
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

    /**
     * <T, K>: Khai báo method generic với 2 kiểu:
        * T → kiểu dữ liệu request gốc
        * K → kiểu dữ liệu phần tử trả về
     * request: Request cố định sẽ truyền vào API mỗi lần gọi
     * apiCall: Một hàm nhận (request, pagingRequest) và trả PagingResponse. Dùng BiFunction để method này không phụ thuộc vào API cụ thể, truyền hàm vào lúc gọi
     * responseType: Class của kiểu dữ liệu trả về (ở đây khai báo nhưng code chưa dùng, có thể để mapping JSON về đúng kiểu K)
     */
    @SuppressWarnings("unchecked")
    private <T, K> List<K> getPagedData(T request, BiFunction<T, PagingRequest, PagingResponse> apiCall, Class<K> responseType) {
        int offset = 0;
        List<K> allData = new ArrayList<>();
        PagingRequest pagingRequest = new PagingRequest();

        while (true) {
            pagingRequest.setOffset(offset * Constants.PAGE_SIZE_DEFAULT);
            PagingResponse pagingRs = apiCall.apply(request, pagingRequest); // Gọi hàm apiCall đã truyền vào, thực chất là gọi API thật
            List<?> rawData  = (List<?>) pagingRs.getData();                 // Lấy data từ response → cast về List<?> vì chưa rõ kiểu
            List<K> typedData  = (List<K>) rawData;                          // Cast tiếp sang List<K> để dùng đúng kiểu generic mà người gọi đã chỉ định
            if (typedData.isEmpty()) {
                break;                                                       // Nếu page hiện tại rỗng → dừng
            }
            allData.addAll(typedData);                                       // Gom dữ liệu của page này vào list tổng

            if (typedData.size() < Constants.PAGE_SIZE_DEFAULT) {
                break;
            }
            offset++;
        }
        return allData;
    }

    private static class InteractionData {
        final List<LikeResponse> likes;
        final List<CommentResponse> comments;
        final List<ShareResponse> shares;

        InteractionData(List<LikeResponse> likes, List<CommentResponse> comments, List<ShareResponse> shares) {
            this.likes = likes;
            this.comments = comments;
            this.shares = shares;
        }

        public List<LikeResponse> getLikes() {
            return likes;
        }

        public List<CommentResponse> getComments() {
            return comments;
        }

        public List<ShareResponse> getShares() {
            return shares;
        }
    }


}

