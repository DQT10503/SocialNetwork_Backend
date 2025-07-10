package com.source_interaction.entity;

import com.source_interaction.utils.enummerate.InteractionStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_comment")
public class TblComment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "content")
    private String content;

    @Column(name = "parent_id")
    private Long parentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public InteractionStatus getStatus() {
        return status;
    }

    public void setStatus(InteractionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblComment [userId=" + userId + ", postId=" + postId + ", content=" + content + ", parentId=" + parentId + ", status=" + status + "]";
    }
}