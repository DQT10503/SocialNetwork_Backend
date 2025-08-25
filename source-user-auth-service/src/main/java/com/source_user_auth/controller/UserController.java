package com.source_user_auth.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_user_auth.domain.user.TblUserDetailResponse;
import com.source_user_auth.domain.user.TblUserRequest;
import com.source_user_auth.domain.user.TblUserResponse;
import com.source_user_auth.domain.user.TblUserUpdateRequest;
import com.source_user_auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "Module User")
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Lấy danh sách user")
    @GetMapping
    public ResponseEntity<PagingResponse> search(TblUserRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Search {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), pagingRequest.getSort(Sort.by(Sort.Direction.ASC, "username")));
        return ResponseEntity.ok(userService.search(request, pageable));
    }

    @ApiOperation(value = "Cập nhật user")
    @GetMapping
    public ResponseEntity<TblUserResponse> update(TblUserUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Search {}", masterAccount, request);
        return ResponseEntity.ok(userService.update(request));
    }

    @ApiOperation(value = "Xóa user")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteMethodResponse> delete(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Detail {}", masterAccount, id);
        return ResponseEntity.ok(userService.delete(id));
    }

    @ApiOperation(value = "Chi tiết user")
    @GetMapping("/{id}")
    public ResponseEntity<TblUserDetailResponse> detail(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Detail {}", masterAccount, id);
        return ResponseEntity.ok(userService.detail(id));
    }
}
