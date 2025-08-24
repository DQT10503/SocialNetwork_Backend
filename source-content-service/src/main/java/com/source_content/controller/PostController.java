package com.source_content.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_content.domain.post.TblPostCreateRequest;
import com.source_content.domain.post.TblPostRequest;
import com.source_content.domain.post.TblPostResponse;
import com.source_content.service.PostService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TblPostResponse> create(@Valid @RequestPart("postRequest") TblPostCreateRequest postRequest,
                                                  @RequestPart(value = "files", required = false) MultipartFile[] files) throws IOException {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Create post {}", masterAccount, postRequest);
        return ResponseEntity.ok(postService.insert(postRequest, files));
    }

}
