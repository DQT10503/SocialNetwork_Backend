package com.source_user_auth.service;

import com.api.framework.domain.PagingResponse;
import com.source_user_auth.domain.user.TblUserRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {
    PagingResponse search(TblUserRequest request, Pageable pageRequest);
}
