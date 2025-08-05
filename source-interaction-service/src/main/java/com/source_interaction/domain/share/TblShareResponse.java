package com.source_interaction.domain.share;

import com.source_interaction.utils.enummerate.InteractionStatus;

public class TblShareResponse {
    private Long id;
    private Long userId;
    private Long postId;
    private Long authorId;
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

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblShareResponse [id=" + id + ", userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", status=" + status + "]";
    }
}
