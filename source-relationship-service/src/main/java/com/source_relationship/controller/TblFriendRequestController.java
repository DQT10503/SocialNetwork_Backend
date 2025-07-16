package com.source_relationship.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_relationship.domain.friend_request.TblFriendRequestCreateRequest;
import com.source_relationship.domain.friend_request.TblFriendRequestResponse;
import com.source_relationship.domain.friend_request.TblFriendRequestUpdateRequest;
import com.source_relationship.service.TblFriendRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module Friend Request")
@RestController
@RequestMapping("/friend-request")
public class TblFriendRequestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblFriendRequestService friendRequestService;

    public TblFriendRequestController(TblFriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @ApiOperation(value = "Thêm mới lời mời kết bạn")
    @PostMapping
    public ResponseEntity<TblFriendRequestResponse> insert(@Valid @RequestBody TblFriendRequestCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(friendRequestService.insert(request));
    }

    @ApiOperation(value = "Cập nhật lời mời kết bạn")
    @PutMapping
    public ResponseEntity<TblFriendRequestResponse> update(@Valid @RequestBody TblFriendRequestUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(friendRequestService.update(request));
    }

    @ApiOperation(value = "Xóa lời mời kết bạn")
    @DeleteMapping("/{senderId}/{receiverId}")
    public ResponseEntity<DeleteMethodResponse> delete(@PathVariable("senderId") Long senderId,
                                                       @PathVariable("receiverId") Long receiverId) {
        String masterAccuont = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {} {}", masterAccuont, senderId, receiverId);
        return ResponseEntity.ok(friendRequestService.delete(senderId, receiverId));
    }
}
