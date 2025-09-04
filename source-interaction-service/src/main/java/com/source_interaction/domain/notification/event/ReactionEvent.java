package com.source_interaction.domain.notification.event;

import com.api.framework.utils.DateTimeUtils;
import com.source_interaction.utils.enummerate.ReactionTargetType;
import com.source_interaction.utils.enummerate.ReactionType;

import java.time.Instant;

public class ReactionEvent {
    private ReactionTargetType type;
    private Long userId;
    private String senderName;
    private Long targetId;
    private Long authorId;
    private ReactionType typeReaction;
    private Instant createdAt;

    public ReactionEvent() {}

    public ReactionEvent(ReactionTargetType type, Long userId, String senderName, Long targetId, Long authorId, ReactionType typeReaction) {
        this.type = type;
        this.userId = userId;
        this.senderName = senderName;
        this.targetId = targetId;
        this.authorId = authorId;
        this.typeReaction = typeReaction;
        this.createdAt = DateTimeUtils.getCurrentTimeUTC();
    }

    public ReactionTargetType getType() {
        return type;
    }

    public void setType(ReactionTargetType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public ReactionType getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(ReactionType typeReaction) {
        this.typeReaction = typeReaction;
    }

    @Override
    public String toString() {
        return "ReactionEvent [type=" + type + ", userId=" + userId + ", senderName=" + senderName + ", targetId=" + targetId + ", authorId=" + authorId + ", typeReaction=" + typeReaction + ", createdAt=" + createdAt + "]";
    }
}
