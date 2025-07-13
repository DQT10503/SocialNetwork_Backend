package com.source_relationship.entity.embedded;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TblFollowerId implements Serializable {
    private static final long serialVersionUID = -8077974806478104078L;
    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @Column(name = "followed_id", nullable = false)
    private Long followedId;

    public TblFollowerId(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TblFollowerId entity = (TblFollowerId) o;
        return Objects.equals(this.followerId, entity.followerId) &&
                Objects.equals(this.followedId, entity.followedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followedId);
    }

}