package com.source_user_auth.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.service.CommonService;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_user_auth.domain.user.TblUserRequest;
import com.source_user_auth.domain.user.TblUserResponse;
import com.source_user_auth.entity.TblUser;
import com.source_user_auth.entity.repository.TblUserRepository;
import com.source_user_auth.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final TblUserRepository userRepository;
    private final CommonService commonService;
    private final MessageUtil messageUtil;

    public UserServiceImpl(TblUserRepository userRepository, CommonService commonService, MessageUtil messageUtil) {
        this.userRepository = userRepository;
        this.commonService = commonService;
        this.messageUtil = messageUtil;
    }

    @Override
    public PagingResponse search(TblUserRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        Map<String, Object> params = new HashMap<>();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.where(whereClause.toString());
        simpleQueryBuilder.from("tbl_user");

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblUser.class);
        List<TblUser> datas = (List<TblUser>) pagingRs.getData();
        List<TblUserResponse> caseResponses = Utilities.copyProperties(datas, TblUserResponse.class);
        pagingRs.setData(caseResponses);
        pagingRs.setTotalRecords(caseResponses.size());
        return pagingRs;
    }
}
