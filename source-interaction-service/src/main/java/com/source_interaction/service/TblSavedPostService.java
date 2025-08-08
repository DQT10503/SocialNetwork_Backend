package com.source_interaction.service;

import com.api.framework.domain.PagingResponse;
import com.source_interaction.domain.saved_post.TblSavedPostRequest;
import com.source_interaction.domain.saved_post.TblSavedPostResponse;
import com.source_interaction.domain.saved_post.TblSavedPostUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface TblSavedPostService {

    PagingResponse search(TblSavedPostRequest request, Pageable pageable);

    TblSavedPostResponse insert(Long postId);

    TblSavedPostResponse update(TblSavedPostUpdateRequest request);

    void delete(Long userId, Long postId);
}
