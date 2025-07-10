package com.source_interaction.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_interaction.domain.comment_like.TblCommentLikeCreateRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeResponse;
import com.source_interaction.domain.comment_like.TblCommentLikeUpdateRequest;
import com.source_interaction.entity.TblCommentLike;
import com.source_interaction.entity.embedded.TblCommentLikeId;
import com.source_interaction.repository.TblCommentLikeRepository;
import com.source_interaction.service.TblCommentLikeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TblCommentLikeServiceImpl implements TblCommentLikeService {
    private final TblCommentLikeRepository commentLikeRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;

    public TblCommentLikeServiceImpl(TblCommentLikeRepository commentLikeRepository, CommonService commonService, MessageUtil messageUtil) {
        this.commentLikeRepository = commentLikeRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PagingResponse search(TblCommentLikeRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("tbl_comment_like");
        simpleQueryBuilder.where(whereClause.toString());

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblCommentLike.class);
        List<TblCommentLike> datas = (List<TblCommentLike>) pagingRs.getData();
        List<TblCommentLikeResponse> caseResponses = Utilities.copyProperties(datas, TblCommentLikeResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblCommentLikeResponse insert(TblCommentLikeCreateRequest request) {
        TblCommentLikeId commentLikeId = new TblCommentLikeId(request.getUserId(), request.getCommentId());
        TblCommentLike commentLike = Utilities.copyProperties(request, TblCommentLike.class);
        commentLike.setId(commentLikeId);
        commentLikeRepository.save(commentLike);
        return Utilities.copyProperties(commentLike, TblCommentLikeResponse.class);
    }

    @Override
    public TblCommentLikeResponse update(TblCommentLikeUpdateRequest request) {
        TblCommentLike commentLike = Utilities.copyProperties(request, TblCommentLike.class);
        commentLikeRepository.save(commentLike);
        return Utilities.copyProperties(commentLike, TblCommentLikeResponse.class);
    }

    @Override
    public void delete(Long userId, Long commentId) {
        TblCommentLikeId id = new TblCommentLikeId(userId, commentId);
        TblCommentLike commentLike = getCommentLikeById(id);
        commentLikeRepository.delete(commentLike);
    }

    private TblCommentLike getCommentLikeById(TblCommentLikeId id) {
        return commentLikeRepository.findById(id).orElseThrow(() ->
                new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "CommentLike ID: " + id));
    }
}
