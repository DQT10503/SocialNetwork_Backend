package com.source_content.service;

import com.api.framework.domain.PagingResponse;
import com.source_content.domain.media.TblMediaCreateRequest;
import com.source_content.domain.post.TblPostCreateRequest;
import com.source_content.domain.post.TblPostRequest;
import com.source_content.domain.post.TblPostResponse;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PagingResponse search(TblPostRequest request, Pageable pageRequest);

    TblPostResponse insert(TblPostCreateRequest postRequest, List<TblMediaCreateRequest> mediaRequest) throws IOException;
}
