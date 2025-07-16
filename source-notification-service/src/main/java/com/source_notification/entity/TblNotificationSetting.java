package com.source_notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbl_notification_setting")
public class TblNotificationSetting implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "like_noti")
    private Boolean likeNoti;

    @Column(name = "comment")
    private Boolean comment;

    @Column(name = "follow")
    private Boolean follow;

    @Column(name = "message")
    private Boolean message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLikeNoti() {
        return likeNoti;
    }

    public void setLikeNoti(Boolean likeNoti) {
        this.likeNoti = likeNoti;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TblNotificationSetting [id=" + id + ", likeNoti=" + likeNoti + ", comment=" + comment + ", follow=" + follow + ", message=" + message + "]";
    }
}