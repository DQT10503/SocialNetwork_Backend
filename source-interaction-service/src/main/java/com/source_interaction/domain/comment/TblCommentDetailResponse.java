package com.source_interaction.domain.comment;


import com.source_interaction.utils.enummerate.InteractionStatus;

import java.time.Instant;

public class TblCommentDetailResponse {
    private Long userId;
    private Long postId;
    private String content;
    private Long parentId;
    private Instant createdAt;
    private Instant updatedAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentDetailResponse [userId=" + userId + ", postId=" + postId + ", content=" + content + ", parentId=" + parentId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + "]";
    }
}
