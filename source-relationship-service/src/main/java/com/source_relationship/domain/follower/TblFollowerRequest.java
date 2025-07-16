package com.source_relationship.domain.follower;

import io.swagger.annotations.ApiModelProperty;

public class TblFollowerRequest {
    @ApiModelProperty(value = "[UserId] người follow")
    private Long followerId;
    @ApiModelProperty(value = "[UserId] người được follow")
    private Long followedId;

    public TblFollowerRequest() {}

    public TblFollowerRequest(Long followerId, Long followedId) {
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
    public String toString() {
        return "TblFollowerRequest [followerId=" + followerId + ", followedId=" + followedId + "]";
    }
}
