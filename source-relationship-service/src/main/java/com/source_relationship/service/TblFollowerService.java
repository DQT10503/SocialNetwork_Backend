package com.source_relationship.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.domain.PagingResponse;
import com.source_relationship.domain.follower.TblFollowerCreateRequest;
import com.source_relationship.domain.follower.TblFollowerRequest;
import com.source_relationship.domain.follower.TblFollowerResponse;
import com.source_relationship.domain.follower.TblFollowerUpdateRequest;
import com.source_relationship.entity.embedded.TblFollowerId;
import org.springframework.data.domain.Pageable;

public interface TblFollowerService {

    PagingResponse search(TblFollowerRequest request, Pageable pageRequest);

    TblFollowerResponse insert(TblFollowerCreateRequest request);

    TblFollowerResponse update(TblFollowerUpdateRequest request);

    DeleteMethodResponse delete(Long followerId, Long followedId);
}
