package com.source_content.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_content.domain.post.TblPostRequest;
import com.source_content.domain.post.TblPostResponse;
import com.source_content.domain.wrapper.PostWrapperCreateRequest;
import com.source_content.service.PostService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Api(description = "Module Post")
@RestController
@RequestMapping("/post")
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PagingResponse> search(TblPostRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Search post {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), pagingRequest.getSort(Sort.by(Sort.Direction.ASC, "content")));
        return ResponseEntity.ok(postService.search(request, pageable));
    }

    @PostMapping
    public ResponseEntity<TblPostResponse> create(@Valid @RequestBody PostWrapperCreateRequest postWrapperRequest) throws IOException {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Create post {} {}", masterAccount, postWrapperRequest, null);
        return ResponseEntity.ok(postService.insert(postWrapperRequest.getPostRequest(), postWrapperRequest.getMediaRequest()));
    }

}
