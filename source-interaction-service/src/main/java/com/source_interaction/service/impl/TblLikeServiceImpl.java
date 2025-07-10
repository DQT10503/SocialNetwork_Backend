package com.source_interaction.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.security.BearerContextHolder;
import com.api.framework.service.CommonService;
import com.api.framework.utils.*;
import com.source_interaction.domain.like.TblLikeRequest;
import com.source_interaction.domain.like.TblLikeResponse;
import com.source_interaction.domain.like.TblLikeUpdateRequest;
import com.source_interaction.domain.post.PostResponse;
import com.source_interaction.domain.like.TblLikeCreateRequest;
import com.source_interaction.domain.user.UserResponse;
import com.source_interaction.entity.TblLike;
import com.source_interaction.entity.embedded.TblLikeId;
import com.source_interaction.domain.event.ReactionEventDTO;
import com.source_interaction.repository.*;
import com.source_interaction.service.TblLikeService;
import com.source_interaction.service.retrofit.PostApiService;
import com.source_interaction.service.retrofit.UserApiService;
import com.source_interaction.utils.enummerate.ReactionTargetType;
import com.source_interaction.utils.topic.KafkaTopics;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TblLikeServiceImpl implements TblLikeService {

    private final TblLikeRepository likeRepository;
    private final PostApiService postApiService;
    private final UserApiService userApiService;
    private final MessageUtil messageUtil;
    private final CommonService commonService;
    private final KafkaTemplate<String, ReactionEventDTO> kafkaTemplate;

    public TblLikeServiceImpl(TblLikeRepository likeRepository, PostApiService postApiService, UserApiService userApiService, MessageUtil messageUtil, CommonService commonService, KafkaTemplate<String, ReactionEventDTO> kafkaTemplate) {
        this.likeRepository = likeRepository;
        this.postApiService = postApiService;
        this.userApiService = userApiService;
        this.messageUtil = messageUtil;
        this.commonService = commonService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PagingResponse search(TblLikeRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("tbl_like");
        simpleQueryBuilder.where(whereClause.toString());

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblLike.class);
        List<TblLike> datas = (List<TblLike>) pagingRs.getData();
        List<TblLikeResponse> caseResponses = Utilities.copyProperties(datas, TblLikeResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblLikeResponse insert(Long postId, TblLikeCreateRequest request) {
        PostResponse post = getPostById(postId);
        UserResponse user = getUser();
        TblLike like = new TblLike();
        like.setId(new TblLikeId(user.getId(), post.getId()));
        like.setStatus(request.getStatus());
        likeRepository.save(like);

        ReactionEventDTO event = new ReactionEventDTO(ReactionTargetType.REACT_POST, user.getId(), post.getId(), like.getStatus());
        kafkaTemplate.send("react-topic", user.getId().toString(), event);
        return Utilities.copyProperties(like, TblLikeResponse.class);
    }

    @Override
    public TblLikeResponse update(TblLikeUpdateRequest request) {
        PostResponse post = getPostById(request.getPostId());
        UserResponse user = getUser();
        TblLikeId id = new TblLikeId(post.getId(), user.getId());
        TblLike like = getLikeById(id);
        Utilities.updateProperties(request, like);
        likeRepository.save(like);
        return Utilities.copyProperties(like, TblLikeResponse.class);
    }

    @Override
    public void delete(Long postId) {
        PostResponse post = getPostById(postId);
        UserResponse user = getUser();
        TblLikeId id = new TblLikeId(user.getId(), post.getId());
        TblLike like = getLikeById(id);
        likeRepository.delete(like);
    }

    private TblLike getLikeById(TblLikeId id) {
        return likeRepository.findById(id).orElseThrow(() ->
                new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "ID: " + id));
    }

    @SuppressWarnings("unchecked")
    protected PostResponse getPostById(Long postId) {
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
    protected UserResponse getUser() {
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
