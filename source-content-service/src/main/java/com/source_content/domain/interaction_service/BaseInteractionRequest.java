package com.source_content.domain.interaction_service;

import com.source_content.utils.enummerate.ContentStatus;
import io.swagger.annotations.ApiModelProperty;

public class BaseInteractionRequest {
    @ApiModelProperty(value = "ID người dùng")
    private Long userId;
    @ApiModelProperty(value = "ID bài đăng")
    private Long postId;
    @ApiModelProperty(value = "ID tác giả")
    private Long authorId;
    @ApiModelProperty(value = "Trạng thái")
    private ContentStatus status;

    public BaseInteractionRequest() {
    }

    public BaseInteractionRequest(Long userId, Long postId, Long authorId, ContentStatus status) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
        this.status = status;
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

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseInteractionRequest [userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", status=" + status + "]";
    }
}
