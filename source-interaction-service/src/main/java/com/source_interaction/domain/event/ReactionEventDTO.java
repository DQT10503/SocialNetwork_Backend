package com.source_interaction.domain.event;

import com.api.framework.utils.DateTimeUtils;
import com.source_interaction.utils.enummerate.ReactionTargetType;
import com.source_interaction.utils.enummerate.ReactionType;

import java.time.Instant;

public class ReactionEventDTO {
    private ReactionTargetType type;
    private Long userId;
    private Long targetId;
    private ReactionType typeReaction;
    private Instant created_at;

    public ReactionEventDTO(ReactionTargetType type, Long userId, Long targetId, ReactionType typeReaction) {
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
        this.typeReaction = typeReaction;
        this.created_at = DateTimeUtils.getCurrentTimeUTC();
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

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public ReactionType getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(ReactionType typeReaction) {
        this.typeReaction = typeReaction;
    }

    @Override
    public String toString() {
        return "ReactionEvent [type=" + type + ", userId=" + userId + ", targetId=" + targetId + ", created_at=" + created_at + "]";
    }
}
