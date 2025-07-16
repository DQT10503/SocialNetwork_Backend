package com.source_relationship.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.source_relationship.domain.friend_request.TblFriendRequestCreateRequest;
import com.source_relationship.domain.friend_request.TblFriendRequestResponse;
import com.source_relationship.domain.friend_request.TblFriendRequestUpdateRequest;

public interface TblFriendRequestService {

    TblFriendRequestResponse insert(TblFriendRequestCreateRequest request);

    TblFriendRequestResponse update(TblFriendRequestUpdateRequest request);

    DeleteMethodResponse delete(Long senderId, Long receiverId);
}
