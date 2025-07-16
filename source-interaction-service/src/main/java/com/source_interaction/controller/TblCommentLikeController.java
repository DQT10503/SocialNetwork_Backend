package com.source_interaction.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.comment_like.TblCommentLikeRequest;
import com.source_interaction.service.TblCommentLikeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Module Like Comment")
@RestController
@RequestMapping("/comment-like")
public class TblCommentLikeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final TblCommentLikeService commentLikeService;


    public TblCommentLikeController(TblCommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @GetMapping
    public ResponseEntity<PagingResponse> search(@RequestBody TblCommentLikeRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageRequest = PageRequest.of(pagingRequest.getLimit(), pagingRequest.getOffset(), Sort.by(Sort.Direction.ASC, "user_id"));
        return ResponseEntity.ok(commentLikeService.search(request, pageRequest));
    }
}
