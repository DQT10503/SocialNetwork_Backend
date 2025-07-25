package com.source_relationship.service.impl;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.*;
import com.source_relationship.domain.follower.TblFollowerCreateRequest;
import com.source_relationship.domain.follower.TblFollowerRequest;
import com.source_relationship.domain.follower.TblFollowerResponse;
import com.source_relationship.domain.follower.TblFollowerUpdateRequest;
import com.source_relationship.entity.TblFollower;
import com.source_relationship.entity.embedded.TblFollowerId;
import com.source_relationship.repository.TblFollowerRepository;
import com.source_relationship.service.TblFollowerService;
import com.source_relationship.utils.enumerate.RelationshipStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TblFollowerServiceImpl implements TblFollowerService {

    private final TblFollowerRepository followerRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;
    private final RedisTemplate<String, String> redisTemplate;

    public TblFollowerServiceImpl(TblFollowerRepository followerRepository, CommonService commonService, MessageUtil messageUtil, RedisTemplate<String, String> redisTemplate) {
        this.followerRepository = followerRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public PagingResponse search(TblFollowerRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        Map<String, Object> params = new HashMap<>();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.where(whereClause.toString());
        simpleQueryBuilder.from("tbl_follower");

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblFollower.class);
        List<TblFollower> datas = (List<TblFollower>) pagingRs.getData();
        List<TblFollowerResponse> caseResponses = Utilities.copyProperties(datas, TblFollowerResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblFollowerResponse insert(TblFollowerCreateRequest request) {
        TblFollowerId followerId = new TblFollowerId(request.getFollowerId(), request.getFollowedId());
        TblFollower follower = new TblFollower();
        follower.setId(followerId);
        follower.setStatus(RelationshipStatus.ACTIVE);
        followerRepository.save(follower);
        redisTemplate.opsForSet().add("user:following:" + request.getFollowerId(), request.getFollowedId().toString());
        return Utilities.copyProperties(follower, TblFollowerResponse.class);
    }

    @Override
    public TblFollowerResponse update(TblFollowerUpdateRequest request) {
        TblFollower follower = getFollowerById(request.getFollowerId(), request.getFollowedId());
        Utilities.updateProperties(request, follower);
        return Utilities.copyProperties(follower, TblFollowerResponse.class);
    }

    @Override
    public DeleteMethodResponse delete(Long followerId, Long followedId) {
        TblFollower follower = getFollowerById(followerId, followedId);
        followerRepository.delete(follower);
        DeleteMethodResponse response = new DeleteMethodResponse();
        response.setId(followerId);
        redisTemplate.opsForSet().remove("user:following:" + followerId, followedId.toString());
        return response;
    }

    private TblFollower getFollowerById(Long followerId, Long followedId) {
        TblFollowerId id = new TblFollowerId(followerId, followedId);
        return followerRepository.findById(id).orElseThrow(() -> new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "FollowerId: " + followerId + ", FollowedId: " + followedId));
    }
}
