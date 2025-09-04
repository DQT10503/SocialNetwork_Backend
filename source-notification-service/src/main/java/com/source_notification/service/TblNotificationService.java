package com.source_notification.service;

import com.source_notification.domain.event.ReactionEvent;

public interface TblNotificationService {
    void handleReaction(ReactionEvent event);
}
