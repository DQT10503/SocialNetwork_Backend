package com.source_interaction.domain.like;

import com.source_interaction.entity.embedded.TblLikeId;
import com.source_interaction.utils.enummerate.ReactionType;

public class TblLikeResponse {
    private TblLikeId id;
    private Long authorId;
    private ReactionType status;

    public TblLikeId getId() {
        return id;
    }

    public void setId(TblLikeId id) {
        this.id = id;
    }

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
        return "TblLikeResponse [id=" + id + ", authorId=" + authorId + ", status=" + status + "]";
    }
}
