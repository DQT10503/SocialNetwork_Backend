package com.source_interaction.domain.saved_post;

import com.source_interaction.utils.enummerate.InteractionStatus;
import io.swagger.annotations.ApiModelProperty;

public class TblSavedPostUpdateRequest {
    @ApiModelProperty(value = "ID người lưu")
    private Long userId;
    @ApiModelProperty(value = "ID bài đăng")
    private Long postId;
    @ApiModelProperty(value = "Trạng thái")
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

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblSavedPostUpdateRequest [userId=" + userId + ", postId=" + postId + ", status=" + status + "]";
    }
}
