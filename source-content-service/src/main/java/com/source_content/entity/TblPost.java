package com.source_content.entity;

import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_post")
public class TblPost extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "privacy_level")
    @Enumerated(EnumType.STRING)
    private PrivacyLevel privacyLevel;

    @Column(name = "location")
    private String location;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
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

    public PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}