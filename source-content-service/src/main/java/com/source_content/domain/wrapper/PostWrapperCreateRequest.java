package com.source_content.domain.wrapper;

import com.source_content.domain.media.TblMediaCreateRequest;
import com.source_content.domain.post.TblPostCreateRequest;

import javax.validation.Valid;
import java.util.List;

public class PostWrapperCreateRequest {
    @Valid
    private TblPostCreateRequest postRequest;

    @Valid
    private List<TblMediaCreateRequest> mediaRequest;

    public TblPostCreateRequest getPostRequest() {
        return postRequest;
    }

    public void setPostRequest(TblPostCreateRequest postRequest) {
        this.postRequest = postRequest;
    }

    public List<TblMediaCreateRequest> getMediaRequest() {
        return mediaRequest;
    }

    public void setMediaRequest(List<TblMediaCreateRequest> mediaRequest) {
        this.mediaRequest = mediaRequest;
    }
}
