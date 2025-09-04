package com.source_notification.kafka.listener;

import com.api.framework.exception.BusinessException;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_notification.domain.event.ReactionEvent;
import com.source_notification.entity.TblNotification;
import com.source_notification.entity.TblNotificationSetting;
import com.source_notification.repository.TblNotificationRepository;
import com.source_notification.repository.TblNotificationSettingRepository;
import com.utils.enummerate.NotificationStatus;
import com.utils.enummerate.ReactionTargetType;
import com.utils.enummerate.ReactionType;
import com.utils.topic.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReactionListener {
    private final TblNotificationRepository notificationRepository;
    private final TblNotificationSettingRepository notificationSettingRepository;
    private final MessageUtil messageUtil;

    public ReactionListener(TblNotificationRepository notificationRepository, TblNotificationSettingRepository notificationSettingRepository, MessageUtil messageUtil) {
        this.notificationRepository = notificationRepository;
        this.notificationSettingRepository = notificationSettingRepository;
        this.messageUtil = messageUtil;
    }

    @KafkaListener(topics = KafkaTopics.REACTION_TOPIC, groupId = "notification-group")
    public void handleReaction(Map<String, Object> map) {
        ReactionEvent event = Utilities.copyProperties(map, ReactionEvent.class);
        TblNotificationSetting notificationSetting = getNotiSettingById(event.getAuthorId());
        if (!notificationSetting.getLikeNoti()) {
            return;
        }
        TblNotification notification = new TblNotification(event.getAuthorId(), event.getType(), buildDataNotification(event.getSenderName(), event.getTypeReaction(), event.getType()), NotificationStatus.ACTIVE);
        notificationRepository.save(notification);
        System.out.println("Received: " + event);
        System.out.println("Messsage: " + buildDataNotification(event.getSenderName(), event.getTypeReaction(), event.getType()));
    }

    private TblNotificationSetting getNotiSettingById(Long id) {
        return notificationSettingRepository.findById(id).orElseThrow(() -> new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "UserId: " + id));
    }

    private String buildDataNotification(String senderName, ReactionType reactionType, ReactionTargetType type) {
        String reactionTarget;
        switch (type) {
            case REACT_POST -> {
                reactionTarget = "bài viết";
                break;
            }
            case REACT_COMMENT -> {
                reactionTarget = "bình luận";
                break;
            }
            case REACT_SHARE -> {
                reactionTarget = "chia sẻ bài viết";
                break;
            }
            default -> {
                reactionTarget = "";
                break;
            }
        }
        StringBuilder data = new StringBuilder(senderName);
        if (reactionType == null) {
            data.append(" đã ").append(reactionTarget).append(" của bạn");
            return data.toString();
        }
        if (ReactionType.LIKE.equals(reactionType)) {
            data.append(" đã thích ").append(reactionTarget).append(" của bạn");
            return data.toString();
        }
        data.append(" đã bày tỏ cảm xúc về ").append(reactionTarget).append(" của bạn");
        return data.toString();
    }
}
