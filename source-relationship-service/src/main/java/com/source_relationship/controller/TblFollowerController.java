package com.source_relationship.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.source_relationship.cache.FollowerCacheService;
import com.source_relationship.domain.follower.TblFollowerCreateRequest;
import com.source_relationship.domain.follower.TblFollowerRequest;
import com.source_relationship.domain.follower.TblFollowerResponse;
import com.source_relationship.domain.follower.TblFollowerUpdateRequest;
import com.source_relationship.service.TblFollowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module Follower Request")
@RestController
@RequestMapping("/follower")
public class TblFollowerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblFollowerService followerService;

    public TblFollowerController(TblFollowerService followerService) {
        this.followerService = followerService;
    }

    @ApiOperation(value = "Filter follower")
    @GetMapping
    public ResponseEntity<PagingResponse> search(TblFollowerRequest request, PagingRequest pagingRequest) throws JsonProcessingException {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), pagingRequest.getSort(Sort.by(Sort.Direction.ASC, "created_at")));
        return ResponseEntity.ok(followerService.search(request, pageable));
    }   

    @ApiOperation(value = "Thêm mới follower")
    @PostMapping
    public ResponseEntity<TblFollowerResponse> insert(@Valid @RequestBody TblFollowerCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(followerService.insert(request));
    }

    @ApiOperation(value = "Cập nhật follower")
    @PutMapping
    public ResponseEntity<TblFollowerResponse> update(@Valid @RequestBody TblFollowerUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(followerService.update(request));
    }

    @ApiOperation(value = "Xóa follower")
    @DeleteMapping("/{followerId}/{followedId}")
    public ResponseEntity<DeleteMethodResponse> delete(@PathVariable Long followerId,
                                                       @PathVariable Long followedId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, followerId);
        return ResponseEntity.ok(followerService.delete(followerId, followedId));
    }
}
