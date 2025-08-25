package com.source_user_auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudinaryUploadResponse {
    @JsonProperty("public_id")
    private String publicId;
    @JsonProperty("secure_url")
    private String secureUrl;
    @JsonProperty("resource_type")
    public String resourceType;
    private String url;
    private String format;
    private int width;
    private int height;
    @JsonProperty("bytes")
    private long size;

    public CloudinaryUploadResponse() {
    }

    public CloudinaryUploadResponse(String publicId, String secureUrl, String resourceType, String url, String format, int width, int height, long size) {
        this.publicId = publicId;
        this.secureUrl = secureUrl;
        this.resourceType = resourceType;
        this.url = url;
        this.format = format;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
