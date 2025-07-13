package com.source_relationship.entity.embedded;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TblFriendRequestId implements Serializable {
    private static final long serialVersionUID = -5932646167380075306L;
    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;

    public TblFriendRequestId(Long senderId, Long receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TblFriendRequestId entity = (TblFriendRequestId) o;
        return Objects.equals(this.senderId, entity.senderId) &&
                Objects.equals(this.receiverId, entity.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, receiverId);
    }

}