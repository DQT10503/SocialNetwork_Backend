package com.source_relationship.entity.embedded;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TblBlockId implements Serializable {
    private static final long serialVersionUID = 7923870481461266864L;
    @Column(name = "blocker_id")
    private Long blockerId;

    @Column(name = "blocked_id")
    private Long blockedId;

    public TblBlockId(Long blockerId, Long blockedId) {
        this.blockerId = blockerId;
        this.blockedId = blockedId;
    }

    public Long getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(Long blockerId) {
        this.blockerId = blockerId;
    }

    public Long getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(Long blockedId) {
        this.blockedId = blockedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TblBlockId entity = (TblBlockId) o;
        return Objects.equals(this.blockerId, entity.blockerId) &&
                Objects.equals(this.blockedId, entity.blockedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockerId, blockedId);
    }

}