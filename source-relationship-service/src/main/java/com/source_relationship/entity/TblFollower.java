package com.source_relationship.entity;

import com.source_relationship.entity.embedded.TblFollowerId;
import com.source_relationship.utils.enumerate.RelationshipStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_follower")
public class TblFollower extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -9156794874570300577L;
    @EmbeddedId
    private TblFollowerId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
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

}