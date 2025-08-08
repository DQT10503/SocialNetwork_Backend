package com.source_interaction.domain.share;

import com.source_interaction.utils.enummerate.InteractionStatus;
import io.swagger.annotations.ApiModelProperty;

public class TblShareUpdateRequest {
    @ApiModelProperty(value = "Share ID")
    private Long id;
    @ApiModelProperty(value = "Nội dung share")
    private String shareText;
    @ApiModelProperty(value = "Trạng thái")
    private InteractionStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblShareUpdateRequest [id=" + id + ", shareText=" + shareText + ", status=" + status + "]";
    }
}
