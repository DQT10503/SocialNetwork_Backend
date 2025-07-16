package com.source_content.domain.media;

import com.source_content.utils.enummerate.ContentStatus;

import java.util.Map;

public class TblMediaCreateRequest {
    private Long postId;
    private String url;
    private String type;
    private Map<String, Object> metaData;

    public TblMediaCreateRequest() {
    }

    public TblMediaCreateRequest(Long postId, String url, String type, Map<String, Object> metaData) {
        this.postId = postId;
        this.url = url;
        this.type = type;
        this.metaData = metaData;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, Object> metaData) {
        this.metaData = metaData;
    }
}
