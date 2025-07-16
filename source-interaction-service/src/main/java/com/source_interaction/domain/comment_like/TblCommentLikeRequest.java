package com.source_interaction.domain.comment_like;

import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

public class TblCommentLikeRequest {
    @ApiModelProperty(value = "ID người dùng")
    private Long userId;
    @ApiModelProperty(value = "ID bình luận")
    private Long commentId;
    @ApiModelProperty(value = "Loại cảm xúc")
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
        return "TblCommentLikeRequest [userId=" + userId + ", commentId=" + commentId + ", status=" + status + "]";
    }
}
