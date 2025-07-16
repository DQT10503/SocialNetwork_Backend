package com.source_interaction.domain.share;

import io.swagger.annotations.ApiModelProperty;

public class TblShareCreateRequest {
    private String shareText;

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }
}
