package com.source_interaction.domain.like;

import com.source_interaction.entity.embedded.TblLikeId;
import com.source_interaction.utils.enummerate.ReactionType;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO for {@link com.source_interaction.entity.TblLike}
 * ID {@link com.source_interaction.entity.embedded.TblLikeId}
 */
public class TblLikeRequest {
    @ApiModelProperty(value = "ID")
    private TblLikeId id;
    @ApiModelProperty(value = "Trạng thái")
    private ReactionType status;

    public TblLikeId getId() {
        return id;
    }

    public void setId(TblLikeId id) {
        this.id = id;
    }

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblLikeRequest [id=" + id + ", status=" + status + "]";
    }
}
