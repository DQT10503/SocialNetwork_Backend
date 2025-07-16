package com.source_relationship.service.impl;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_relationship.domain.friend_request.TblFriendRequestCreateRequest;
import com.source_relationship.domain.friend_request.TblFriendRequestResponse;
import com.source_relationship.domain.friend_request.TblFriendRequestUpdateRequest;
import com.source_relationship.entity.TblFriendRequest;
import com.source_relationship.entity.embedded.TblFriendRequestId;
import com.source_relationship.repository.TblFriendRequestRepository;
import com.source_relationship.service.TblFriendRequestService;
import com.source_relationship.utils.enumerate.RelationshipStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblFriendRequestServiceImpl implements TblFriendRequestService {

    private final TblFriendRequestRepository friendRequestRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;

    public TblFriendRequestServiceImpl(TblFriendRequestRepository friendRequestRepository, CommonService commonService, MessageUtil messageUtil) {
        this.friendRequestRepository = friendRequestRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
    }

    @Override
    public TblFriendRequestResponse insert(TblFriendRequestCreateRequest request) {
        TblFriendRequest friendRequest = Utilities.copyProperties(request, TblFriendRequest.class);
        friendRequest.setStatus(RelationshipStatus.ACTIVE);
        friendRequestRepository.save(friendRequest);
        return Utilities.copyProperties(friendRequest, TblFriendRequestResponse.class);
    }

    @Override
    public TblFriendRequestResponse update(TblFriendRequestUpdateRequest request) {
        TblFriendRequest friendRequest = getFriendRequestById(request.getSenderId(), request.getReceiverId());
        Utilities.updateProperties(request, friendRequest);
        return Utilities.copyProperties(friendRequest, TblFriendRequestResponse.class);
    }

    @Override
    public DeleteMethodResponse delete(Long senderId, Long receiverId) {
        TblFriendRequest friendRequest = getFriendRequestById(senderId, receiverId);
        friendRequestRepository.delete(friendRequest);
        DeleteMethodResponse response = new DeleteMethodResponse();
        response.setId(senderId);
        return response;
    }

    private TblFriendRequest getFriendRequestById(Long senderId, Long receiverId) {
        TblFriendRequestId id = new TblFriendRequestId(senderId, receiverId);
        return friendRequestRepository.findById(id).orElseThrow(() -> new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "SenderId: " + senderId + ", ReceiverId: " + receiverId));
    }
}
