package com.source_relationship.domain.friend_request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblFriendRequestCreateRequest {
    @ApiModelProperty(value = "Đây là UserId, đại diện cho nguời gửi lời mời kết bạn")
    @NotNull
    private Long senderId;
    @ApiModelProperty(value = "Đây là UserId, đại diện cho người nhận lời mời kết bạn")
    @NotNull
    private Long receiverId;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "TblFriendRequestCreateRequest [senderId=" + senderId + ", receiverId=" + receiverId + "]";
    }
}
