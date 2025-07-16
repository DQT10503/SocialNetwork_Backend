package com.source_content.domain.post;

import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;

public class TblPostResponse {
    private Long id;
    private Long userId;
    private String content;
    private String location;
    private PrivacyLevel privacyLevel;
    private ContentStatus status;

    public TblPostResponse() {
    }

    public TblPostResponse(Long id, String content, PrivacyLevel privacyLevel, ContentStatus status) {
        this.id = id;
        this.content = content;
        this.privacyLevel = privacyLevel;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



}
