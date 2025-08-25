package com.source_user_auth.service.impl;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_user_auth.domain.user_profile.TblUserProfileCreateRequest;
import com.source_user_auth.domain.user_profile.TblUserProfileDetailResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileResponse;
import com.source_user_auth.domain.user_profile.TblUserProfileUpdateRequest;
import com.source_user_auth.entity.TblUserProfile;
import com.source_user_auth.entity.repository.TblUserProfileRepository;
import com.source_user_auth.service.UserProfileService;
import com.source_user_auth.utils.enummerate.AuthStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TblUserProfileServiceImpl implements UserProfileService {

    private final MessageUtil messageUtil;
    private final TblUserProfileRepository userProfileRepository;

    public TblUserProfileServiceImpl(MessageUtil messageUtil, TblUserProfileRepository userProfileRepository) {
        this.messageUtil = messageUtil;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public TblUserProfileResponse insert(TblUserProfileCreateRequest request) {
        TblUserProfile userProfile = Utilities.copyProperties(request, TblUserProfile.class);
        userProfile.setStatus(AuthStatus.ACTIVE);
        userProfileRepository.save(userProfile);
        return Utilities.copyProperties(userProfile, TblUserProfileResponse.class);
    }

    @Override
    public TblUserProfileResponse update(TblUserProfileUpdateRequest request) {
        TblUserProfile userProfile = getUserProfileById(request.getId());
        Utilities.updateProperties(request, userProfile);
        userProfileRepository.save(userProfile);
        return Utilities.copyProperties(userProfile, TblUserProfileResponse.class);
    }

    @Override
    public TblUserProfileDetailResponse detail(Long id) {
        TblUserProfile userProfile = getUserProfileById(id);
        return Utilities.copyProperties(userProfile, TblUserProfileDetailResponse.class);
    }

    @Override
    public DeleteMethodResponse delete(Long id) {
        TblUserProfile userProfile = getUserProfileById(id);
        userProfileRepository.deleteById(id);
        DeleteMethodResponse response = new DeleteMethodResponse();
        response.setId(id);
        return response;
    }

    private TblUserProfile getUserProfileById(Long id) {
        return userProfileRepository.findById(id).orElseThrow(() -> new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "ID: " + id));
    }
}
