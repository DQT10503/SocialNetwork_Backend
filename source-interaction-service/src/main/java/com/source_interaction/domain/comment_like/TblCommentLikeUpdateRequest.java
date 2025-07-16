package com.source_interaction.domain.comment_like;

import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

public class TblCommentLikeUpdateRequest {
    @ApiModelProperty(value = "Loại cảm xúc")
    private ReactionType status;

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentLikeUpdateRequest [status=" + status + "]";
    }
}
