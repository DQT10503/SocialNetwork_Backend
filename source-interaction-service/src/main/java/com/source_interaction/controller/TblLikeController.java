package com.source_interaction.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.like.TblLikeCreateRequest;
import com.source_interaction.domain.like.TblLikeRequest;
import com.source_interaction.domain.like.TblLikeResponse;
import com.source_interaction.domain.like.TblLikeUpdateRequest;
import com.source_interaction.service.TblLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "Module tương tác bài viết")
@RestController
@RequestMapping("/interaction/like")
public class TblLikeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblLikeService likeService;

    public TblLikeController(TblLikeService likeService) {
        this.likeService = likeService;
    }

    @ApiOperation(value = "Danh sách like")
    @GetMapping
    public ResponseEntity<PagingResponse> search(@RequestBody TblLikeRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount,  request);
        Pageable pageRequest = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), Sort.by(Sort.Direction.ASC, "created_at"));
        return ResponseEntity.ok(likeService.search(request, pageRequest));
    }

    @ApiOperation(value = "Like bài đăng")
    @PostMapping("/{postId}")
    public ResponseEntity<TblLikeResponse> insert(@RequestBody TblLikeCreateRequest request,
                                                  @PathVariable("postId") Long postId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(likeService.insert(postId, request));
    }

    @ApiOperation(value = "Sửa like")
    @PutMapping
    public ResponseEntity<TblLikeResponse> update(@RequestBody TblLikeUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(likeService.update(request));
    }

    @ApiModelProperty(value = "Xóa like")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable("postId") Long postId) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, postId);
        return ResponseEntity.noContent().build();
    }


}
