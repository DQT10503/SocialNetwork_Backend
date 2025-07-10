package com.source_interaction.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.source_interaction.domain.comment.*;
import org.springframework.data.domain.Pageable;

public interface TblCommentService {
    PagingResponse search(TblCommentRequest request, Pageable pageRequest);

    TblCommentResponse insert(Long postId, TblCommentCreateRequest request);

    TblCommentResponse update(TblCommentUpdateRequest request);

    DeleteMethodResponse delete(Long id);

    TblCommentDetailResponse detail(Long id);

}
