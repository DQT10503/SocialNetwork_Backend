package com.source_notification.domain.notification_setting;

public class TblNotificationSettingResponse {
    private Long id;
    private Boolean likeNoti;
    private Boolean comment;
    private Boolean follow;
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
        return "TblNotificationSettingResponse [userId=" + id + ", likeNoti=" + likeNoti + ", comment=" + comment + ", follow=" + follow + ", message=" + message + "]";
    }
}
