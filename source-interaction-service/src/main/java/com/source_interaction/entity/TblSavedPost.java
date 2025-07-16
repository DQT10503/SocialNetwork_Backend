package com.source_interaction.entity;

import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.DateTimeUtils;
import com.source_interaction.entity.embedded.TblSavedPostId;
import com.source_interaction.utils.enummerate.InteractionStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tbl_saved_post")
public class TblSavedPost implements Serializable {
    private static final long serialVersionUID = 6139576189240067127L;
    @EmbeddedId
    private TblSavedPostId id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InteractionStatus status;

    public TblSavedPostId getId() {
        return id;
    }

    public void setId(TblSavedPostId id) {
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

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblSavedPost [id=" + id + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + ", status=" + status + "]";
    }

    @PrePersist
    public void preInsert() {
        this.createdAt = DateTimeUtils.getCurrentTimeUTC();
        this.createdBy = BearerContextHolder.getContext().getMasterAccount();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = DateTimeUtils.getCurrentTimeUTC();
        this.updatedBy = BearerContextHolder.getContext().getMasterAccount();
    }
}