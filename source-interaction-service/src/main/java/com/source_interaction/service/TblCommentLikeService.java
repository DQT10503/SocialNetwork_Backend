package com.source_interaction.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.source_interaction.domain.comment_like.TblCommentLikeCreateRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeResponse;
import com.source_interaction.domain.comment_like.TblCommentLikeUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface TblCommentLikeService {
    PagingResponse search(TblCommentLikeRequest request, Pageable pageRequest);

    TblCommentLikeResponse insert(TblCommentLikeCreateRequest request);

    TblCommentLikeResponse update(TblCommentLikeUpdateRequest request);

    void delete(Long userId, Long commentId);
}
