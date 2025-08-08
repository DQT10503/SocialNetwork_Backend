package com.source_interaction.domain.saved_post;

import com.source_interaction.utils.enummerate.InteractionStatus;

public class TblSavedPostResponse extends TblSavedPostRequest {
    private InteractionStatus status;

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    public TblSavedPostResponse() {
        super();
    }

    public TblSavedPostResponse(Long userId, Long postId) {
        super(userId, postId);
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
    public String toString() {
        return super.toString();
    }
}
