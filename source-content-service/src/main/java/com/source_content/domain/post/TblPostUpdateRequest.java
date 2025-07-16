package com.source_content.domain.post;

import com.source_content.utils.enummerate.ContentStatus;
import com.source_content.utils.enummerate.PrivacyLevel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblPostUpdateRequest extends TblPostCreateRequest {
    @ApiModelProperty("Post ID")
    @NotNull
    private Long id;
    @ApiModelProperty("Trạng thái")
    private ContentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    @Override
    public Long getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(Long userId) {
        super.setUserId(userId);
    }

    @Override
    public String getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    public PrivacyLevel getPrivacyLevel() {
        return super.getPrivacyLevel();
    }

    @Override
    public void setPrivacyLevel(PrivacyLevel privacyLevel) {
        super.setPrivacyLevel(privacyLevel);
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(String location) {
        super.setLocation(location);
    }

    @Override
    public String toString() {
        return "TblPostUpdateRequest [id=" + id + ", status=" + status + "]";
    }
}
