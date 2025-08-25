package com.source_user_auth.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileCreateRequest;
import com.source_user_auth.domain.user_profile.TblUserProfileDetailResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileUpdateRequest;

public interface UserProfileService {

    TblUserProfileResponse insert(TblUserProfileCreateRequest request);

    TblUserProfileResponse update(TblUserProfileUpdateRequest request);

    TblUserProfileDetailResponse detail(Long id);

    DeleteMethodResponse delete(Long id);
}
