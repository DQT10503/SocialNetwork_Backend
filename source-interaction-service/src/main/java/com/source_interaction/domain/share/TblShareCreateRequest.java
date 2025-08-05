package com.source_interaction.domain.share;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblShareCreateRequest {
    @ApiModelProperty(value = "Nội dung share")
    private String shareText;
    @ApiModelProperty(value = "ID tác giả")
    @NotNull
    private Long authorId;

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "TblShareCreateRequest [shareText=" + shareText + ", authorId=" + authorId + "]";
    }
}
