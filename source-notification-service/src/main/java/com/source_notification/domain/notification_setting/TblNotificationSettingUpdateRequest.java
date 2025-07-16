package com.source_notification.domain.notification_setting;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblNotificationSettingUpdateRequest {
    @ApiModelProperty(value = "ID người dùng")
    @NotNull
    private Long id;
    @ApiModelProperty(value = "Thông báo lượt thích")
    private Boolean likeNoti;
    @ApiModelProperty(value = "Thông báo lượt thích")
    private Boolean comment;
    @ApiModelProperty(value = "Thông báo người theo dõi")
    private Boolean follow;
    @ApiModelProperty(value = "Thông báo tin nhắn")
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
        return "TblNotificationSettingUpdateRequest [id=" + id + ", likeNoti=" + likeNoti + ", comment=" + comment + ", follow=" + follow + ", message=" + message + "]";
    }
}
