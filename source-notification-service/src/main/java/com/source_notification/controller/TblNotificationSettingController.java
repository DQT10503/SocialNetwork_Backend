package com.source_notification.controller;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_notification.domain.notification_setting.TblNotificationSettingRequest;
import com.source_notification.domain.notification_setting.TblNotificationSettingResponse;
import com.source_notification.domain.notification_setting.TblNotificationSettingUpdateRequest;
import com.source_notification.service.TblNotificationSettingService;
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

@Api(description = "Module Notification Setting")
@RestController
@RequestMapping("/noti-setting")
public class TblNotificationSettingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblNotificationSettingService notiSettingService;

    public TblNotificationSettingController(TblNotificationSettingService notiSettingService) {
        this.notiSettingService = notiSettingService;
    }

    @ApiOperation(value = "Lấy danh sách notification setting")
    @GetMapping
    public ResponseEntity<PagingResponse> search(TblNotificationSettingRequest request, PagingRequest pageRequest) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Filter {}", masterAccount, request);
        Pageable pageable = PageRequest.of(pageRequest.getOffset(), pageRequest.getLimit());
        return ResponseEntity.ok(notiSettingService.search(request, pageable));
    }

    @ApiOperation(value = "Thêm mới notification setting")
    @PostMapping
    public ResponseEntity<TblNotificationSettingResponse> insert(@Valid @RequestBody TblNotificationSettingRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(notiSettingService.insert(request));
    }

    @ApiOperation(value = "Cập nhật notification setting")
    @PutMapping
    public ResponseEntity<TblNotificationSettingResponse> update(@Valid @RequestBody TblNotificationSettingUpdateRequest request) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Insert {}", masterAccount, request);
        return ResponseEntity.ok(notiSettingService.update(request));
    }

    @ApiOperation(value = "Chi tiết notification setting")
    @GetMapping("/{id}")
    public ResponseEntity<TblNotificationSettingResponse> detail(@PathVariable("id") Long id) {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} Delete {}", masterAccount, id);
        return ResponseEntity.ok(notiSettingService.detail(id));
    }
}