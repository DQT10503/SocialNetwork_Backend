package com.source_notification.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_notification.domain.notification_setting.TblNotificationSettingRequest;
import com.source_notification.domain.notification_setting.TblNotificationSettingResponse;
import com.source_notification.domain.notification_setting.TblNotificationSettingUpdateRequest;
import com.source_notification.entity.TblNotification;
import com.source_notification.entity.TblNotificationSetting;
import com.source_notification.repository.TblNotificationSettingRepository;
import com.source_notification.service.TblNotificationSettingService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TblNotificationSettingServiceImpl implements TblNotificationSettingService {
    private final TblNotificationSettingRepository notificationSettingRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;

    public TblNotificationSettingServiceImpl(TblNotificationSettingRepository notificationSettingRepository, CommonService commonService, MessageUtil messageUtil) {
        this.notificationSettingRepository = notificationSettingRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
    }

    @Override
    public PagingResponse search(TblNotificationSettingRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("tbl_notification_setting");
        simpleQueryBuilder.where(whereClause.toString());

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblNotificationSetting.class);
        List<TblNotification> datas = (List<TblNotification>) pagingRs.getData();
        List<TblNotificationSettingResponse> caseResponses = Utilities.copyProperties(datas, TblNotificationSettingResponse.class);
        pagingRs.setData(caseResponses);
        return pagingRs;
    }

    @Override
    public TblNotificationSettingResponse insert(TblNotificationSettingRequest request) {
        TblNotificationSetting notificationSetting = Utilities.copyProperties(request, TblNotificationSetting.class);
        notificationSettingRepository.save(notificationSetting);
        return Utilities.copyProperties(notificationSetting, TblNotificationSettingResponse.class);
    }

    @Override
    public TblNotificationSettingResponse update(TblNotificationSettingUpdateRequest request) {
        TblNotificationSetting notiSetting = getNotiSetting(request.getId());
        Utilities.updateProperties(request, notiSetting);
        notificationSettingRepository.save(notiSetting);
        return Utilities.copyProperties(notiSetting, TblNotificationSettingResponse.class);
    }

    @Override
    public TblNotificationSettingResponse detail(Long id) {
        TblNotificationSetting notiSetting = getNotiSetting(id);
        return Utilities.copyProperties(notiSetting, TblNotificationSettingResponse.class);
    }

    private TblNotificationSetting getNotiSetting(Long id) {
        return notificationSettingRepository.findById(id).orElseThrow(()->
                new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "User ID: " + id));
    }
}
