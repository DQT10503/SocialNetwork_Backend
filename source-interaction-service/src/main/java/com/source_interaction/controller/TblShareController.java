package com.source_interaction.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_interaction.domain.share.TblShareCreateRequest;
import com.source_interaction.domain.share.TblShareRequest;
import com.source_interaction.domain.share.TblShareResponse;
import com.source_interaction.domain.share.TblShareUpdateRequest;
import com.source_interaction.service.TblShareService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module Share")
@RestController
@RequestMapping(value = "/interaction/share")
public class TblShareController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblShareService shareService;


    public TblShareController(TblShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    public ResponseEntity<PagingResponse> search(TblShareRequest request, PagingRequest pagingRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pagingRequest.getOffset(), pagingRequest.getLimit(), Sort.by(Sort.Direction.DESC, "created_at"));
        return ResponseEntity.ok(shareService.search(request, pageable));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<TblShareResponse> insert(@PathVariable("postId") Long postId,
                                                   @Valid @RequestBody TblShareCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {} {}", masterAccount, postId, request);
        return ResponseEntity.ok(shareService.insert(postId, request));
    }

    @PutMapping
    public ResponseEntity<TblShareResponse> update(@Valid @RequestBody TblShareUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(shareService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, id);
        shareService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
