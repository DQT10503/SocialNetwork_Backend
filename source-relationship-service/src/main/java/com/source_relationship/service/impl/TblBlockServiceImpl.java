package com.source_relationship.service.impl;

import com.api.framework.domain.DeleteMethodResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_relationship.domain.block.TblBlockCreateRequest;
import com.source_relationship.domain.block.TblBlockResponse;
import com.source_relationship.domain.block.TblBlockUpdateRequest;
import com.source_relationship.entity.TblBlock;
import com.source_relationship.entity.embedded.TblBlockId;
import com.source_relationship.repository.TblBlockRepository;
import com.source_relationship.service.TblBlockService;
import com.source_relationship.utils.enumerate.RelationshipStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblBlockServiceImpl implements TblBlockService {

    private final TblBlockRepository blockRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;

    public TblBlockServiceImpl(TblBlockRepository blockRepository, CommonService commonService, MessageUtil messageUtil) {
        this.blockRepository = blockRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
    }

    @Override
    public TblBlockResponse insert(TblBlockCreateRequest request) {
        TblBlock block = Utilities.copyProperties(request, TblBlock.class);
        block.setStatus(RelationshipStatus.ACTIVE);
        return Utilities.copyProperties(block, TblBlockResponse.class);
    }

    @Override
    public TblBlockResponse update(TblBlockUpdateRequest request) {
        TblBlock block = getBlockById(request.getBlockerId(), request.getBlockedId());
        Utilities.updateProperties(request, block);
        blockRepository.save(block);
        return Utilities.copyProperties(block, TblBlockResponse.class);
    }

    @Override
    public DeleteMethodResponse delete(Long blockerId, Long blockedId) {
        TblBlock block = getBlockById(blockerId, blockedId);
        blockRepository.delete(block);
        DeleteMethodResponse response = new DeleteMethodResponse();
        response.setId(blockerId);
        return response;
    }

    private TblBlock getBlockById(Long blockerId, Long blockedId) {
        TblBlockId blockId = new TblBlockId(blockedId, blockedId);
        return blockRepository.findById(blockId).orElseThrow(() -> new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "BlockerId: " + blockerId + ", BlockedId: " + blockedId));
    }
}
