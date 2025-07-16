package com.source_content.domain.post;

import com.source_content.utils.enummerate.ContentStatus;

import java.util.Map;

public class TblPostRequest {
    private Long id;
    private Long userId;
    private String content;
    private String location;
    private ContentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblPostRequest [id=" + id + ", userId=" + userId + ", content=" + content + ", location=" + location + ", status=" + status + "]";
    }
}
