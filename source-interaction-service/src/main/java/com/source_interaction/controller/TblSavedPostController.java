package com.source_interaction.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.saved_post.TblSavedPostRequest;
import com.source_interaction.domain.saved_post.TblSavedPostResponse;
import com.source_interaction.domain.saved_post.TblSavedPostUpdateRequest;
import com.source_interaction.domain.share.TblShareCreateRequest;
import com.source_interaction.service.TblSavedPostService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module SavedPost")
@RestController
@RequestMapping(value = "/interaction/savedpost")
public class TblSavedPostController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblSavedPostService savedPostService;

    public TblSavedPostController(TblSavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    @GetMapping
    public ResponseEntity<PagingResponse> search(TblSavedPostRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), pagingRequest.getSort(Sort.by(Sort.Direction.DESC, "created_at")));
        return ResponseEntity.ok(savedPostService.search(request, pageable));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<TblSavedPostResponse> insert(@PathVariable("postId") Long postId,
                                                       @Valid @RequestBody TblShareCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {} {}", masterAccount, postId, request);
        return ResponseEntity.ok(savedPostService.insert(postId));
    }

    @PutMapping
    public ResponseEntity<TblSavedPostResponse> update(@Valid @RequestBody TblSavedPostUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(savedPostService.update(request));
    }

    @DeleteMapping("/userId/{userId}/postId/{postId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId,
                                       @PathVariable("postId") Long postId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {} {}", masterAccount, userId, postId);
        savedPostService.delete(userId, postId);
        return ResponseEntity.noContent().build();
    }
}
