package com.source_interaction.domain.like;

import com.api.framework.utils.converter.DateTimeJsonDeserializer;
import com.api.framework.utils.converter.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.source_interaction.entity.embedded.TblLikeId;
import com.source_interaction.utils.enummerate.ReactionType;

import java.time.Instant;

public class TblLikeResponse {
    private TblLikeId id;
    private Long authorId;
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private Instant createdAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }



}
