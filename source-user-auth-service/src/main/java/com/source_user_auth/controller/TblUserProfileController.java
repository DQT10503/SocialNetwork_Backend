package com.source_user_auth.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_user_auth.domain.user_profile.TblUserProfileCreateRequest;
import com.source_user_auth.domain.user_profile.TblUserProfileDetailResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileUpdateRequest;
import com.source_user_auth.service.UserProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module UserProfile")
@RestController
@RequestMapping("/user-profile")
public class TblUserProfileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserProfileService userProfileService;

    public TblUserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @ApiOperation(value = "Thêm mới UserProfile")
    @PostMapping
    public ResponseEntity<TblUserProfileResponse> insert(@Valid @RequestBody TblUserProfileCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(userProfileService.insert(request));
    }

    @ApiOperation(value = "Cập nhật UserProfile")
    @PutMapping
    public ResponseEntity<TblUserProfileResponse> update(@Valid @RequestBody TblUserProfileUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(userProfileService.update(request));
    }

    @ApiOperation(value = "Xóa UserProfile")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteMethodResponse> delete(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, id);
        return ResponseEntity.ok(userProfileService.delete(id));
    }

    @ApiOperation(value = "Chi tiết UserProfile")
    @GetMapping("/{id}")
    public ResponseEntity<TblUserProfileDetailResponse> detail(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, id);
        return ResponseEntity.ok(userProfileService.detail(id));
    }

}
