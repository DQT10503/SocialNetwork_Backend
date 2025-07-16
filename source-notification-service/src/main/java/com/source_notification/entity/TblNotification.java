package com.source_notification.entity;

import com.api.framework.security.BearerContextHolder;
import com.api.framework.utils.DateTimeUtils;
import com.utils.enummerate.NotificationStatus;

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
    private String type;

    @Column(name = "data")
    private String data;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;

    @PrePersist
    public void preInsert() {
        this.createdAt = DateTimeUtils.getCurrentTimeUTC();
        this.createdBy = BearerContextHolder.getContext().getMasterAccount();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblNotification [id=" + id + ", userId=" + userId + ", type=" + type + ", data=" + data + ", isRead=" + isRead + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", status=" + status + "]";
    }
}