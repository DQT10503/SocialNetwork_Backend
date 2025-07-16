package com.source_interaction.domain.comment;


import com.api.framework.utils.Annotations;
import com.source_interaction.utils.enummerate.InteractionStatus;
import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

public class TblCommentRequest {
    @ApiModelProperty(value = "Comment ID")
    private Long id;
    @ApiModelProperty(value = "User ID")
    private Long userId;
    @ApiModelProperty(value = "Post ID")
    private Long postId;
    @ApiModelProperty(value = "Nội dung")
    @Annotations.Operator(value = "LIKE")
    private String content;
    @ApiModelProperty(value = "Bình luận cha")
    private Long parentId;
    @ApiModelProperty(value = "Ngày tạo")
    private Instant createdAt;
    @ApiModelProperty(value = "Trạng thái")
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentRequest [id=" + id + ", userId=" + userId + ", postId=" + postId + ", content=" + content + ", parentId=" + parentId + ", createdAt=" + createdAt + ", status=" + status + "]";
    }
}
