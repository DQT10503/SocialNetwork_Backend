package com.source_relationship.cache.impl;

import com.api.framework.service.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.source_relationship.cache.FollowerCacheService;
import com.source_relationship.domain.UserWithFollowedResponse;
import com.source_relationship.utils.enumerate.RelationshipStatus;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.*;

@Service
public class FollowerCacheServiceImpl implements FollowerCacheService {
    private final StringRedisTemplate stringRedisTemplate;
    private final CommonService commonService;
    private final ObjectMapper objectMapper;

    public FollowerCacheServiceImpl(StringRedisTemplate stringRedisTemplate, CommonService commonService, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.commonService = commonService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void cacheUserWithFollowed() throws JsonProcessingException {
        String sql = ""
                + "     SELECT follower_id AS user_id, followed_id AS followed_user_id"
                + "     FROM tbl_follower"
                + "     WHERE status = :status"
                + "     UNION ALL"
                + "     SELECT sender_id AS user_id, receiver_id AS followed_user_id"
                + "     FROM tbl_friend_request"
                + "     WHERE status = :status";
        Map<String, Object> params = new HashMap<>();
        params.put("status", RelationshipStatus.ACTIVE.toString());
        List<Tuple> tuples = commonService.executeGetListTuple(sql, params);
        for (Tuple tuple : tuples) {
            Long userId =  ((BigInteger) tuple.get("user_id")).longValue();
            Long followedId = ((BigInteger) tuple.get("followed_user_id")).longValue();
            stringRedisTemplate.opsForSet().add("user:following:" + userId, followedId.toString());
        }
    }
}
