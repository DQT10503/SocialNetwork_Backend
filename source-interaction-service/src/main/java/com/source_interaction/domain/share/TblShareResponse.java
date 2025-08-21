package com.source_interaction.domain.share;

import com.api.framework.utils.converter.DateTimeJsonDeserializer;
import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.source_interaction.utils.enummerate.InteractionStatus;

import java.time.Instant;

public class TblShareResponse {
    private Long id;
    private Long userId;
    private Long postId;
    private Long authorId;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant createdAt;
    private InteractionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblShareResponse [id=" + id + ", userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", createdAt=" + createdAt + ", status=" + status + "]";
    }
}
