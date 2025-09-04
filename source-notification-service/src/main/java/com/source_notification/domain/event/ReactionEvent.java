package com.source_notification.domain.event;

import com.api.framework.utils.converter.DateTimeJsonDeserializer;
import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.utils.enummerate.ReactionTargetType;
import com.utils.enummerate.ReactionType;

import java.time.Instant;

public class ReactionEvent {
    private Long userId;
    private String senderName;
    private Long targetId;
    private Long authorId;
    private ReactionType typeReaction;
    private ReactionTargetType type;
    private Instant createdAt;

    public ReactionEvent() {
    }

    public ReactionEvent(Long userId, String senderName, Long targetId, Long authorId, ReactionType typeReaction, ReactionTargetType type, Instant createdAt) {
        this.userId = userId;
        this.senderName = senderName;
        this.targetId = targetId;
        this.authorId = authorId;
        this.typeReaction = typeReaction;
        this.type = type;
        this.createdAt = createdAt;
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

    public ReactionType getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(ReactionType typeReaction) {
        this.typeReaction = typeReaction;
    }

    public ReactionTargetType getType() {
        return type;
    }

    public void setType(ReactionTargetType type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ReactionEventDTO [userId=" + userId + ", targetId=" + targetId + ", authorId=" + authorId + ", typeReaction=" + typeReaction + ", type=" + type + ", createdAt=" + createdAt + "]";
    }
}
