package com.source_interaction.domain.like;

import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO for {@link com.source_interaction.entity.TblLike}
 * ID {@link com.source_interaction.entity.embedded.TblLikeId}
 */
public class TblLikeRequest {
    @ApiModelProperty(value = "ID người like")
    private Long userId;
    @ApiModelProperty(value = "ID bài đăng")
    private Long postId;
    @ApiModelProperty(value = "ID tác giả")
    private Long authorId;
    @ApiModelProperty(value = "Trạng thái")
    private ReactionType status;

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

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblLikeRequest [userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", status=" + status + "]";
    }
}
