package com.source_interaction.service;

import com.source_interaction.domain.notification.NotificationEvent;

public interface KafkaService {
    void sendNotification(NotificationEvent event);

}
