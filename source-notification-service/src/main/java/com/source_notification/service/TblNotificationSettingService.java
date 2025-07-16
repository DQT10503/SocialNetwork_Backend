package com.source_notification.service;

import com.api.framework.domain.PagingResponse;
import com.source_notification.domain.notification_setting.TblNotificationSettingRequest;
import com.source_notification.domain.notification_setting.TblNotificationSettingResponse;
import com.source_notification.domain.notification_setting.TblNotificationSettingUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface TblNotificationSettingService {
    PagingResponse search(TblNotificationSettingRequest request, Pageable pageRequest);

    TblNotificationSettingResponse insert(TblNotificationSettingRequest request);

    TblNotificationSettingResponse update(TblNotificationSettingUpdateRequest request);

    TblNotificationSettingResponse detail(Long id);
}
