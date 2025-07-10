package com.source_interaction.entity;

import com.source_interaction.entity.embedded.TblCommentLikeId;
import com.source_interaction.utils.enummerate.ReactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tbl_comment_like")
public class TblCommentLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TblCommentLikeId id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReactionType status;

    public TblCommentLikeId getId() {
        return id;
    }

    public void setId(TblCommentLikeId id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ReactionType getStatus() {
        return status;
    }

    public void setStatus(ReactionType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblCommentLike [id=" + id + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + ", status=" + status + "]";
    }
}