package com.source_notification.service;

import com.source_notification.domain.event.ReactionEventDTO;

public interface TblNotificationService {
    void handleReaction(ReactionEventDTO event);
}
