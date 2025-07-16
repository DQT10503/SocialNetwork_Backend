package com.source_notification.domain.event;

import com.utils.enummerate.ReactionTargetType;

import java.time.Instant;

public class ReactionEventDTO {
    private Long userId;
    private Long targetId;
    private String typeReaction;
    private ReactionTargetType type;
    private Instant createdAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(String typeReaction) {
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
        return "ReactionEventDTO [userId=" + userId + ", targetId=" + targetId + ", typeReaction=" + typeReaction + ", type=" + type + ", createdAt=" + createdAt + "]";
    }
}
