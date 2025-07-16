package com.source_relationship.service;

import com.api.framework.domain.DeleteMethodResponse;
import com.source_relationship.domain.block.TblBlockCreateRequest;
import com.source_relationship.domain.block.TblBlockResponse;
import com.source_relationship.domain.block.TblBlockUpdateRequest;
import com.source_relationship.entity.embedded.TblBlockId;

public interface TblBlockService {

    TblBlockResponse insert(TblBlockCreateRequest request);

    TblBlockResponse update(TblBlockUpdateRequest request);

    DeleteMethodResponse delete(Long blockerId, Long blockedId);
}
