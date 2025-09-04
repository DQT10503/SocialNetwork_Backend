package com.source_notification.service.impl;

import com.source_notification.domain.event.ReactionEvent;
import com.source_notification.service.TblNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblNotificationServiceImpl implements TblNotificationService {
    @Override
    public void handleReaction(ReactionEvent event) {
        System.out.println("Đã gửi thông báo thành công");
    }
}
