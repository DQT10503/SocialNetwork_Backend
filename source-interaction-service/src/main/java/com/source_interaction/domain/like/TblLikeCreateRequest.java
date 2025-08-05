package com.source_interaction.domain.like;

import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblLikeCreateRequest {
    @ApiModelProperty(value = "ID tác giả")
    @NotNull
    private Long authorId;
    @ApiModelProperty(value = "Loại cảm xúc")
    @NotNull
    private ReactionType status;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblLikeCreateRequest [authorId=" + authorId + ", status=" + status + "]";
    }
}
