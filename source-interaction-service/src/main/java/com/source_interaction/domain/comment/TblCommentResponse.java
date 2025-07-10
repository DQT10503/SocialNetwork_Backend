package com.source_interaction.domain.comment;

import com.source_interaction.utils.enummerate.InteractionStatus;

public class TblCommentResponse {
    private Long userId;
    private Long postId;
    private String content;
    private Long parentId;
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

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentResponse [userId=" + userId + ", postId=" + postId + ", content=" + content + ", parentId=" + parentId + ", status=" + status + "]";
    }
}
