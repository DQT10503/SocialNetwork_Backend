package com.source_notification.controller;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.source_notification.domain.notification_setting.TblNotificationSettingRequest;
import com.source_notification.service.TblNotificationSettingService;
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

@Api(description = "Module Notification Setting")
@RestController
@RequestMapping("/noti-setting")
public class TblNotificationSettingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TblNotificationSettingService notiSettingService;

    public TblNotificationSettingController(TblNotificationSettingService notiSettingService) {
        this.notiSettingService = notiSettingService;
    }


}
