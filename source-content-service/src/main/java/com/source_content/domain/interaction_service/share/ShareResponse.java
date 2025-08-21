package com.source_content.domain.interaction_service.share;

import com.source_content.domain.interaction_service.BaseInteractionResponse;

import java.time.Instant;

public class ShareResponse extends BaseInteractionResponse {
    public ShareResponse() {
        super();
    }

    public ShareResponse(Long userId, Long postId, Long authorId, Instant createdAt) {
        super(userId, postId, authorId, createdAt);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(Long userId) {
        super.setUserId(userId);
    }

    @Override
    public Long getPostId() {
        return super.getPostId();
    }

    @Override
    public void setPostId(Long postId) {
        super.setPostId(postId);
    }

    @Override
    public Long getAuthorId() {
        return super.getAuthorId();
    }

    @Override
    public void setAuthorId(Long authorId) {
        super.setAuthorId(authorId);
    }

    @Override
    public Instant getCreatedAt() {
        return super.getCreatedAt();
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        super.setCreatedAt(createdAt);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
