package com.source_relationship.domain.follower;

import com.source_relationship.utils.enumerate.RelationshipStatus;

public class TblFollowerResponse {
    private Long followerId;
    private Long followedId;
    private RelationshipStatus status;

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

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblFollowerResponse [followerId=" + followerId + ", followedId=" + followedId + ", status=" + status + "]";
    }
}
