package com.source_user_auth.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.source_user_auth.domain.user.TblUserDetailResponse;
import com.source_user_auth.domain.user.TblUserRequest;
import com.source_user_auth.domain.user.TblUserResponse;
import com.source_user_auth.domain.user.TblUserUpdateRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {
    PagingResponse search(TblUserRequest request, Pageable pageRequest);

    TblUserResponse update(TblUserUpdateRequest request);

    DeleteMethodResponse delete(Long id);

    TblUserDetailResponse detail(Long id);
}
