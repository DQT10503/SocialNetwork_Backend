package com.source_interaction.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.comment.TblCommentCreateRequest;
import com.source_interaction.domain.comment.TblCommentRequest;
import com.source_interaction.domain.comment.TblCommentResponse;
import com.source_interaction.domain.comment.TblCommentUpdateRequest;
import com.source_interaction.service.TblCommentService;
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

@Api(description = "Module Comment")
@RestController
@RequestMapping("/comment")
public class TblCommentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblCommentService commentService;

    public TblCommentController(TblCommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Danh sách Comment")
    @GetMapping
    public ResponseEntity<PagingResponse> search(TblCommentRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getLimit(), pagingRequest.getOffset(), Sort.by(Sort.Direction.DESC, "created_at"));
        return ResponseEntity.ok(commentService.search(request, pageable));
    }

    @ApiOperation(value = "Thêm mới Comment")
    @PostMapping("/{postId}")
    public ResponseEntity<TblCommentResponse> insert(@Valid @RequestBody TblCommentCreateRequest request,
                                                     @PathVariable Long postId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(commentService.insert(postId, request));
    }

    @ApiOperation(value = "Cập nhật Comment")
    @PutMapping
    public ResponseEntity<TblCommentResponse> update(@Valid @RequestBody TblCommentUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(commentService.update(request));
    }

    @ApiOperation(value = "Xóa Comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteMethodResponse> delete(@PathVariable Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, id);
        return ResponseEntity.ok(commentService.delete(id));
    }
    
}
