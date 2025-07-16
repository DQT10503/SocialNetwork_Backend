package com.source_interaction.domain.comment;

import com.source_interaction.utils.enummerate.InteractionStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblCommentUpdateRequest {
    @ApiModelProperty(value = "Comment ID")
    @NotNull
    private Long id;
    @ApiModelProperty(value = "Nội dung")
    @NotNull
    private String content;
    @ApiModelProperty(value = "Trạng thái")
    private InteractionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentUpdateRequest [id=" + id + ", content=" + content + ", status=" + status + "]";
    }
}
