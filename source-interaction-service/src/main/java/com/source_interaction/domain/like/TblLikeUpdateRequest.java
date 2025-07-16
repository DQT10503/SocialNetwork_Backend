package com.source_interaction.domain.like;

import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblLikeUpdateRequest extends TblLikeCreateRequest {

    @ApiModelProperty(value = "Id bài đăng")
    @NotNull
    private Long postId;

    @Override
    public ReactionType getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(ReactionType status) {
        super.setStatus(status);
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
