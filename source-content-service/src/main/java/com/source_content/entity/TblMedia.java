package com.source_content.entity;

import com.source_content.utils.enummerate.ContentStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "tbl_media")
public class TblMedia extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

    @Type(type = "json")
    @Column(name = "meta_data", columnDefinition = "json")
    private Map<String, Object> metaData;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ContentStatus status;

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

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

}