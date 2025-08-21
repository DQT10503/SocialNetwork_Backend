package com.source_content.domain.interaction_service;

import com.api.framework.utils.converter.DateTimeJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.source_content.utils.enummerate.ContentStatus;
import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

public class BaseInteractionResponse {
    private Long userId;
    private Long postId;
    private Long authorId;
    @JsonDeserialize(using = DateTimeJsonDeserializer.class)
    private Instant createdAt;

    public BaseInteractionResponse() {
    }

    public BaseInteractionResponse(Long userId, Long postId, Long authorId, Instant createdAt) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    @Override
    public String toString() {
        return "BaseInteractionResponse [userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", createdAt=" + createdAt + "]";
    }
}
