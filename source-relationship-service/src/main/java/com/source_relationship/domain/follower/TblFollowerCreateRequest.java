package com.source_relationship.domain.follower;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblFollowerCreateRequest {

    @ApiModelProperty(value = "Đây là UserId, đại diện cho ID của nguời dùng theo dõi")
    @NotNull
    private Long followerId;

    @ApiModelProperty(value = "Cũng là UserId, đại diện cho ID của nguời dùng bị theo dõi")
    @NotNull
    private Long followedId;

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
        return "TblFollowerCreateRequest [followerId=" + followerId + ", followedId=" + followedId + "]";
    }
}
