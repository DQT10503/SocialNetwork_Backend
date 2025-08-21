package com.source_content.domain.interaction_service.like;

import com.source_content.domain.interaction_service.BaseInteractionRequest;
import com.source_content.utils.enummerate.ContentStatus;
public class LikeRequest extends BaseInteractionRequest {
    public LikeRequest() {
    }

    public LikeRequest(ContentStatus contentStatus) {
        super();
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
    public ContentStatus getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(ContentStatus status) {
        super.setStatus(status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
