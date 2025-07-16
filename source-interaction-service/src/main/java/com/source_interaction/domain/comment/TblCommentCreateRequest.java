package com.source_interaction.domain.comment;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link com.source_interaction.entity.TblComment}
 */
public class TblCommentCreateRequest {
    @ApiModelProperty(value = "Nội dung")
    @NotNull
    private String content;
    @ApiModelProperty(value = "Comment cha")
    private Long parentId;

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

    @Override
    public String toString() {
        return "TblCommentCreateRequest [content=" + content + ", parentId=" + parentId + "]";
    }
}