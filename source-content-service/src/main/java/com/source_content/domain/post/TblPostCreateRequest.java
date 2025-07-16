package com.source_content.domain.post;

import com.source_content.utils.enummerate.PrivacyLevel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class TblPostCreateRequest {
    @ApiModelProperty(value = "User ID")
    @NotNull
    private Long userId;
    @ApiModelProperty(value = "Nội dung")
    @NotNull
    private String content;
    @ApiModelProperty(value = "Quyền riêng tư")
    @NotNull
    private PrivacyLevel privacyLevel;
    @ApiModelProperty(value = "Vị trí")
    private String location;

    public TblPostCreateRequest() {}

    public TblPostCreateRequest(Long userId, String content, PrivacyLevel privacyLevel, String location) {
        this.userId = userId;
        this.content = content;
        this.privacyLevel = privacyLevel;
        this.location = location;
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

    public PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(PrivacyLevel privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TblPostCreateRequest [userId=" + userId + ", content=" + content + ", privacyLevel=" + privacyLevel + ", location=" + location + "]";
    }
}
