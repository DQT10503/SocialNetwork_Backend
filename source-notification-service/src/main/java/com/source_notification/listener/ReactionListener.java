package com.source_notification.listener;

import com.source_notification.domain.event.ReactionEventDTO;
import com.source_notification.service.TblNotificationService;
import com.utils.enummerate.ReactionTargetType;
import com.utils.topic.KafkaTopics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ReactionListener {
    private final TblNotificationService notificationService;
    private final String REACTION = ReactionTargetType.REACT_POST.getValue();

    public ReactionListener(TblNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "react-topic",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleReaction(ConsumerRecord<String, ReactionEventDTO> record, Acknowledgment ack) {
        System.out.println("Hello");
    }

}
