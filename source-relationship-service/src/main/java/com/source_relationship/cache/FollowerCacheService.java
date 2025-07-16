package com.source_relationship.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.source_relationship.domain.follower.TblFollowerResponse;

public interface FollowerCacheService {

    void cacheUserWithFollowed() throws JsonProcessingException;
}
