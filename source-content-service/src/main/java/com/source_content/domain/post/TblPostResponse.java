package com.source_content.domain.post;

import com.source_content.entity.TblMedia;
import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;

import java.util.List;

public class TblPostResponse {
    private Long id;
    private Long userId;
    private String content;
    private String location;
    private PrivacyLevel privacyLevel;
    private ContentStatus status;
    private List<TblMedia> media;

    public TblPostResponse() {
    }

    public TblPostResponse(Long id, Long userId, String content, String location, PrivacyLevel privacyLevel, ContentStatus status, List<TblMedia> media) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.location = location;
        this.privacyLevel = privacyLevel;
        this.status = status;
        this.media = media;
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

    public List<TblMedia> getMedia() {
        return media;
    }

    public void setMedia(List<TblMedia> media) {
        this.media = media;
    }
}
