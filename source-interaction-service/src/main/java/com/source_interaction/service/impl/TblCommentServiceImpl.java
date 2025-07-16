package com.source_interaction.service.impl;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.*;
import com.source_interaction.domain.comment.*;
import com.source_interaction.domain.post.PostResponse;
import com.source_interaction.domain.user.UserResponse;
import com.source_interaction.entity.TblComment;
import com.source_interaction.repository.TblCommentRepository;
import com.source_interaction.service.TblCommentService;
import com.source_interaction.utils.enummerate.InteractionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class TblCommentServiceImpl implements TblCommentService {
    private final CommonService commonService;
    private final MessageUtil messageUtil;
    private final TblCommentRepository commentRepository;
    private final TblLikeServiceImpl likeServiceImpl;

    public TblCommentServiceImpl(CommonService commonService, MessageUtil messageUtil, TblCommentRepository commentRepository, TblLikeServiceImpl likeServiceImpl) {
        this.commonService = commonService;
        this.messageUtil = messageUtil;
        this.commentRepository = commentRepository;
        this.likeServiceImpl = likeServiceImpl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PagingResponse search(TblCommentRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder(" "
                + " WITH RECURSIVE comment_hierarchy AS ("
                + "     SELECT *, id as path"
                + "     FROM tbl_comment"
                + "     WHERE parent_id IS NULL"
                + " UNION ALL"
                + "     SELECT c.*, ch.path || ' > ' || c.name AS path"
                + "     FROM tbl_comment c"
                + "     JOIN comment_hierarchy ch ON c.parent_id = ch.parent_id"
                + " )"
                + " SELECT * FROM comment_hierarchy"
        );
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("tbl_comment");
        simpleQueryBuilder.where(whereClause.toString());
        simpleQueryBuilder.orderBy("path", Sort.Direction.ASC);

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblComment.class);
        List<TblComment> datas = (List<TblComment>) pagingRs.getData();
        List<TblCommentResponse> caseResponses = Utilities.copyProperties(datas, TblCommentResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblCommentResponse insert(Long postId, TblCommentCreateRequest request) {
        PostResponse post = likeServiceImpl.getPostById(postId);
        UserResponse user = likeServiceImpl.getUser();
        TblComment comment = Utilities.copyProperties(request, TblComment.class);
        comment.setPostId(post.getId());
        comment.setUserId(user.getId());
        comment.setStatus(InteractionStatus.ACTIVE);
        commentRepository.save(comment);
        return Utilities.copyProperties(comment, TblCommentResponse.class);
    }

    @Override
    public TblCommentResponse update(TblCommentUpdateRequest request) {
        TblComment comment = getCommentById(request.getId());
        Utilities.updateProperties(request, comment);
        return Utilities.copyProperties(comment, TblCommentResponse.class);
    }

    @Override
    public DeleteMethodResponse delete(Long id) {
        TblComment comment = getCommentById(id);
        if (Objects.nonNull(comment) && InteractionStatus.ACTIVE.equals(comment.getStatus())) {
            throw new BusinessException(MessageCode.ERR_STATUS, messageUtil.getMessage(MessageCode.ERR_STATUS), "Status: " + comment.getStatus());
        }
        DeleteMethodResponse deleteMethodResponse = new DeleteMethodResponse();
        deleteMethodResponse.setId(id);
        commentRepository.delete(comment);
        return deleteMethodResponse;
    }

    @Override
    public TblCommentDetailResponse detail(Long id) {
        TblComment comment = getCommentById(id);
        if (InteractionStatus.INACTIVE.equals(comment.getStatus())) {
            throw new BusinessException(MessageCode.ERR_STATUS, messageUtil.getMessage(MessageCode.ERR_STATUS), "Status: " + comment.getStatus());
        }
        return Utilities.copyProperties(comment, TblCommentDetailResponse.class);
    }

    private TblComment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "Comment ID: " + id));
    }
}
