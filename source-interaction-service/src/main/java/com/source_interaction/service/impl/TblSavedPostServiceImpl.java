package com.source_interaction.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_interaction.domain.post.PostResponse;
import com.source_interaction.domain.saved_post.TblSavedPostRequest;
import com.source_interaction.domain.saved_post.TblSavedPostResponse;
import com.source_interaction.domain.saved_post.TblSavedPostUpdateRequest;
import com.source_interaction.domain.user.UserResponse;
import com.source_interaction.entity.TblSavedPost;
import com.source_interaction.entity.embedded.TblSavedPostId;
import com.source_interaction.repository.TblSavedPostRepository;
import com.source_interaction.service.TblSavedPostService;
import com.source_interaction.service.retrofit.CallApiExternalService;
import com.source_interaction.utils.enummerate.InteractionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TblSavedPostServiceImpl implements TblSavedPostService {
    private final TblSavedPostRepository savedPostRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;
    private final CallApiExternalService callApiExternalService;

    public TblSavedPostServiceImpl(TblSavedPostRepository savedPostRepository, CommonService commonService, MessageUtil messageUtil, CallApiExternalService callApiExternalService) {
        this.savedPostRepository = savedPostRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
        this.callApiExternalService = callApiExternalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PagingResponse search(TblSavedPostRequest request, Pageable pageable) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("public.tbl_share");
        simpleQueryBuilder.from(whereClause.toString());

        PagingResponse pagingRs = commonService.executeSearchData(pageable, simpleQueryBuilder, params, TblSavedPost.class);
        List<TblSavedPost> datas = (List<TblSavedPost>) pagingRs.getData();
        List<TblSavedPostResponse> caseResponses = Utilities.copyProperties(datas, TblSavedPostResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblSavedPostResponse insert(Long postId) {
        PostResponse post = callApiExternalService.getPostById(postId);
        UserResponse user = callApiExternalService.getUser();
        TblSavedPostId savedPostId = new TblSavedPostId(post.getId(), user.getId());
        TblSavedPost savedPost = new TblSavedPost();
        savedPost.setId(savedPostId);
        savedPost.setStatus(InteractionStatus.ACTIVE);
        savedPostRepository.save(savedPost);
        return Utilities.copyProperties(savedPost, TblSavedPostResponse.class);
    }

    @Override
    public TblSavedPostResponse update(TblSavedPostUpdateRequest request) {
        TblSavedPost savedPost = getSavedPostById(request.getUserId(), request.getPostId());
        Utilities.updateProperties(request, savedPost);
        savedPostRepository.save(savedPost);
        return Utilities.copyProperties(savedPost, TblSavedPostResponse.class);
    }

    @Override
    public void delete(Long userId, Long postId) {
        TblSavedPost savedPost = getSavedPostById(userId, postId);
        savedPostRepository.delete(savedPost);
    }

    private TblSavedPost getSavedPostById(Long userId, Long postId) {
        TblSavedPostId savedPostId = new TblSavedPostId(userId, postId);
        return savedPostRepository.findById(savedPostId).orElseThrow(() ->
                new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "UserId: " + userId + ", PostId:" + postId));
    }
}
