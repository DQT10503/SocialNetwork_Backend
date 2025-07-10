package com.source_interaction.domain.comment_like;

import com.source_interaction.utils.enummerate.ReactionType;

import java.time.Instant;

public class TblCommentLikeResponse {
    private Long userId;
    private Long commentId;
    private ReactionType status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentLikeResponse [userId=" + userId + ", commentId=" + commentId + ", status=" + status + "]";
    }
}
