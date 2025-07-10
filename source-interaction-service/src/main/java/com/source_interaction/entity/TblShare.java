package com.source_interaction.entity;

import com.source_interaction.utils.enummerate.InteractionStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_share")
public class TblShare extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "share_text")
    private String shareText;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InteractionStatus status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblShare [userId=" + userId + ", postId=" + postId + ", shareText=" + shareText + ", status=" + status + "]";
    }
}