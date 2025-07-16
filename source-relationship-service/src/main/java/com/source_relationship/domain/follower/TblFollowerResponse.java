package com.source_relationship.domain.follower;

import com.source_relationship.entity.embedded.TblFollowerId;
import com.source_relationship.utils.enumerate.RelationshipStatus;

public class TblFollowerResponse {
    private TblFollowerId id;
    private RelationshipStatus status;

    public TblFollowerId getId() {
        return id;
    }

    public void setId(TblFollowerId id) {
        this.id = id;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblFollowerResponse [id=" + id + ", status=" + status + "]";
    }
}
