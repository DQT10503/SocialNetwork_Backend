package com.source_relationship.domain;

import java.util.List;
import java.util.Set;

public class UserWithFollowedResponse {
    private Long userId;
    private Set<Long> followed;

    public UserWithFollowedResponse() {
    }

    public UserWithFollowedResponse(Long userId, Set<Long> followed) {
        this.userId = userId;
        this.followed = followed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<Long> followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "UserWithFollowedResponse [userId=" + userId + ", followed=" + followed + "]";
    }
}
