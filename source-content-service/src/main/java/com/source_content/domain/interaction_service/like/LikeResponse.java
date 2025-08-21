package com.source_content.domain.interaction_service.like;

import com.source_content.domain.interaction_service.BaseInteractionResponse;

import java.time.Instant;

public class LikeResponse extends BaseInteractionResponse {
    private LikeId id;

    public LikeResponse() {}

    public LikeResponse(Long userId, Long postId, Long authorId, Instant createdAt) {
        super(userId, postId, authorId, createdAt);
        this.id = new LikeId(userId, postId);
    }

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
        this.id = id;
        if (id != null) {
            super.setUserId(id.getUserId());
            super.setPostId(id.getPostId());
        }
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
