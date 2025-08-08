package com.source_interaction.service;

import com.api.framework.domain.PagingResponse;
import com.source_interaction.domain.share.TblShareCreateRequest;
import com.source_interaction.domain.share.TblShareRequest;
import com.source_interaction.domain.share.TblShareResponse;
import com.source_interaction.domain.share.TblShareUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface TblShareService {

    PagingResponse search(TblShareRequest request, Pageable pageable);

    TblShareResponse insert(Long postId, TblShareCreateRequest request);

    TblShareResponse update(TblShareUpdateRequest request);

    void delete(Long id);
}
