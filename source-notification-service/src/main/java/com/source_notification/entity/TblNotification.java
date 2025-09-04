package com.source_notification.entity;

import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.DateTimeUtils;
import com.utils.enummerate.NotificationStatus;
import com.utils.enummerate.ReactionTargetType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tbl_notification")
public class TblNotification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "type")
    private ReactionTargetType type;

    @Column(name = "data")
    private String data;

    @Column(name = "is_read")
    private Boolean isRead;

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
    private NotificationStatus status;

    public TblNotification() {
    }

    public TblNotification(Long id, Long userId, ReactionTargetType type, String data, Boolean isRead, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, NotificationStatus status) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.data = data;
        this.isRead = isRead;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    public TblNotification(Long userId, ReactionTargetType type, String data, NotificationStatus status) {
        this.userId = userId;
        this.type = type;
        this.data = data;
        this.status = status;
    }

    @PrePersist
    public void preInsert() {
        this.isRead = false;
        this.createdAt = DateTimeUtils.getCurrentTimeUTC();
        this.createdBy = BearerContextHolder.getContext().getMasterAccount();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = DateTimeUtils.getCurrentTimeUTC();
        this.updatedBy = BearerContextHolder.getContext().getMasterAccount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ReactionTargetType getType() {
        return type;
    }

    public void setType(ReactionTargetType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
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

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblNotification [id=" + id + ", userId=" + userId + ", type=" + type + ", data=" + data + ", isRead=" + isRead + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + ", status=" + status + "]";
    }
}