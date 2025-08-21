package com.source_interaction.domain.comment;

import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.source_interaction.utils.enummerate.InteractionStatus;

import java.time.Instant;

public class TblCommentResponse {
    private Long userId;
    private Long postId;
    private String content;
    private Long parentId;
    private Long authorId;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant createdAt;
    private InteractionStatus status;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        return "TblCommentResponse [userId=" + userId + ", postId=" + postId + ", content=" + content + ", parentId=" + parentId + ", authorId=" + authorId + ", createdAt=" + createdAt + ", status=" + status + "]";
    }
}
