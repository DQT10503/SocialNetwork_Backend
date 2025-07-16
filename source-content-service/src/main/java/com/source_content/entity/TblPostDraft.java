package com.source_content.entity;

import com.source_content.utils.enummerate.ContentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tbl_post_draft")
public class TblPostDraft extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2522609284369043733L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContentStatus status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

}