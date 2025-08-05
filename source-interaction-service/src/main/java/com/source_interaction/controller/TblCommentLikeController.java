package com.source_interaction.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.comment_like.TblCommentLikeCreateRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeRequest;
import com.source_interaction.domain.comment_like.TblCommentLikeResponse;
import com.source_interaction.domain.comment_like.TblCommentLikeUpdateRequest;
import com.source_interaction.service.TblCommentLikeService;
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

@Api(value = "Module Like Comment")
@RestController
@RequestMapping("/comment-like")
public class TblCommentLikeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final TblCommentLikeService commentLikeService;


    public TblCommentLikeController(TblCommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @ApiOperation(value = "Danh sách react comment")
    @GetMapping
    public ResponseEntity<PagingResponse> search(TblCommentLikeRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageRequest = PageRequest.of(pagingRequest.getLimit(), pagingRequest.getOffset(), Sort.by(Sort.Direction.ASC, "user_id"));
        return ResponseEntity.ok(commentLikeService.search(request, pageRequest));
    }

    @ApiOperation(value = "Thêm mới react comeent")
    @PostMapping
    public ResponseEntity<TblCommentLikeResponse> insert(@Valid @RequestBody TblCommentLikeCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(commentLikeService.insert(request));
    }

    @ApiOperation(value = "Cập nhật react comment")
    @PutMapping
    public ResponseEntity<TblCommentLikeResponse> update(@Valid @RequestBody TblCommentLikeUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(commentLikeService.update(request));
    }

    @ApiOperation(value = "Xóa react comment")
    @DeleteMapping("/userId/{userId}/commentId/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId,
                                       @PathVariable Long commentId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {} {}", masterAccount, userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
