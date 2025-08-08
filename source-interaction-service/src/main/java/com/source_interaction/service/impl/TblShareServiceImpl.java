package com.source_interaction.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.exception.BusinessException;
import com.api.framework.service.CommonService;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_interaction.domain.post.PostResponse;
import com.source_interaction.domain.share.TblShareCreateRequest;
import com.source_interaction.domain.share.TblShareRequest;
import com.source_interaction.domain.share.TblShareResponse;
import com.source_interaction.domain.share.TblShareUpdateRequest;
import com.source_interaction.domain.user.UserResponse;
import com.source_interaction.entity.TblShare;
import com.source_interaction.repository.TblShareRepository;
import com.source_interaction.service.TblShareService;
import com.source_interaction.service.retrofit.CallApiExternalService;
import com.source_interaction.utils.enummerate.InteractionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TblShareServiceImpl implements TblShareService {
    private final TblShareRepository shareRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;
    private final CallApiExternalService callApiExternalService;

    public TblShareServiceImpl(TblShareRepository shareRepository, CommonService commonService, MessageUtil messageUtil, CallApiExternalService callApiExternalService) {
        this.shareRepository = shareRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
        this.callApiExternalService = callApiExternalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PagingResponse search(TblShareRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.from("tbl_share");
        simpleQueryBuilder.where(whereClause.toString());

       PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblShare.class);
       List<TblShare> datas = (List<TblShare>) pagingRs.getData();
       List<TblShareResponse> caseResponses = Utilities.copyProperties(datas, TblShareResponse.class);
       pagingRs.setData(caseResponses);
       return pagingRs;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public TblShareResponse insert(Long postId, TblShareCreateRequest request) {
        UserResponse user = callApiExternalService.getUser();
        PostResponse post = callApiExternalService.getPostById(postId);
        TblShare share = Utilities.copyProperties(request, TblShare.class);
        share.setPostId(post.getId());
        share.setUserId(user.getId());
        share.setStatus(InteractionStatus.ACTIVE);
        shareRepository.save(share);
        return Utilities.copyProperties(share, TblShareResponse.class);
    }

    @Override
    public TblShareResponse update(TblShareUpdateRequest request) {
        TblShare share = getShareById(request.getId());
        Utilities.updateProperties(request, share);
        shareRepository.save(share);
        return Utilities.copyProperties(share, TblShareResponse.class);
    }

    @Override
    public void delete(Long id) {
        TblShare share = getShareById(id);
        shareRepository.delete(share);
    }

    private TblShare getShareById(Long id) {
        return shareRepository.findById(id).orElseThrow(() ->
                new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "ID: " + id));
    }
}
