package com.source_interaction.domain.share;

import com.source_interaction.utils.enummerate.InteractionStatus;
import io.swagger.annotations.ApiModelProperty;

public class TblShareRequest {
    @ApiModelProperty(value = "Share ID")
    private Long id;

    @ApiModelProperty(value = "ID người share")
    private Long userId;

    @ApiModelProperty(value = "ID post")
    private Long postId;

    @ApiModelProperty(value = "ID tác giả")
    private Long authorId;

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblShareRequest [id=" + id + ", userId=" + userId + ", postId=" + postId + ", authorId=" + authorId + ", status=" + status + "]";
    }
}
