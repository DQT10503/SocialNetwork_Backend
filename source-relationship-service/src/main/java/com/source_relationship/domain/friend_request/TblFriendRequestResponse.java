package com.source_relationship.domain.friend_request;

import com.source_relationship.utils.enumerate.RelationshipStatus;

public class TblFriendRequestResponse {

    private Long senderId;
    private Long receiverId;
    private RelationshipStatus status;

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

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblFriendRequestResponse [senderId=" + senderId + ", receiverId=" + receiverId + ", status=" + status + "]";
    }
}
