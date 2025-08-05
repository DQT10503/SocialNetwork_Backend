package com.source_interaction.domain.share;

import io.swagger.annotations.ApiModelProperty;

public class TblShareUpdateRequest {
    @ApiModelProperty(value = "Nội dung share")
    private String shareText;

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    @Override
    public String toString() {
        return "TblShareUpdateRequest [shareText=" + shareText + "]";
    }
}
