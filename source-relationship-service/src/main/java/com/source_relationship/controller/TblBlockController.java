package com.source_relationship.controller;

import com.api.framework.security.BearerContextHolder;
import com.source_relationship.domain.block.TblBlockCreateRequest;
import com.source_relationship.domain.block.TblBlockResponse;
import com.source_relationship.domain.block.TblBlockUpdateRequest;
import com.source_relationship.service.TblBlockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Module Block")
@RestController
@RequestMapping("/block")
public class TblBlockController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblBlockService blockService;

    public TblBlockController(TblBlockService blockService) {
        this.blockService = blockService;
    }

    @ApiOperation(value = "Thêm mới block")
    @PostMapping
    public ResponseEntity<TblBlockResponse> insert(@Valid @RequestBody TblBlockCreateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(blockService.insert(request));
    }

    @ApiOperation(value = "Cập nhật block")
    @PutMapping
    public ResponseEntity<TblBlockResponse> update(@Valid @RequestBody TblBlockUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Update {}", masterAccount, request);
        return ResponseEntity.ok(blockService.update(request));
    }
}
