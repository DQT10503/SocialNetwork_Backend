package com.source_content.service.impl;

import com.api.framework.domain.PagingResponse;
import com.api.framework.security.BearerContextHolder;
import com.api.framework.service.CommonService;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.SimpleQueryBuilder;
import com.api.framework.utils.Utilities;
import com.source_content.domain.media.TblMediaCreateRequest;
import com.source_content.domain.post.TblPostCreateRequest;
import com.source_content.domain.post.TblPostRequest;
import com.source_content.domain.post.TblPostResponse;
import com.source_content.domain.user.UserResponse;
import com.source_content.entity.TblMedia;
import com.source_content.entity.TblPost;
import com.source_content.repository.TblMediaRepository;
import com.source_content.repository.TblPostDraftRepository;
import com.source_content.repository.TblPostRepository;
import com.source_content.service.PostService;
import com.source_content.service.retrofit.UserApiService;
import com.source_content.utils.enummerate.ContentStatus;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class PostServiceImpl implements PostService {
    private final MessageUtil messageUtil;
    private final CommonService commonService;
    private final TblPostRepository postRepository;
    private final TblPostDraftRepository postDraftRepository;
    private final TblMediaRepository mediaRepository;
    private final UserApiService userApiService;

    public PostServiceImpl(MessageUtil messageUtil, CommonService commonService, TblPostRepository postRepository, TblPostDraftRepository postDraftRepository, TblMediaRepository mediaRepository, UserApiService userApiService) {
        this.messageUtil = messageUtil;
        this.commonService = commonService;
        this.postRepository = postRepository;
        this.postDraftRepository = postDraftRepository;
        this.mediaRepository = mediaRepository;
        this.userApiService = userApiService;
    }


    @Override
    public PagingResponse search(TblPostRequest request, Pageable pageRequest) {
        StringBuilder whereClause = new StringBuilder("1 = 1");
        SimpleQueryBuilder simpleQueryBuilder = new SimpleQueryBuilder();
        Map<String, Object> params = new HashMap<>();
        whereClause.append(Utilities.buildWhereClause(request, params));

        simpleQueryBuilder.where(whereClause.toString());
        simpleQueryBuilder.from("tbl_post");

        PagingResponse pagingRs = commonService.executeSearchData(pageRequest, simpleQueryBuilder, params, TblPost.class);
        List<TblPost> datas = (List<TblPost>) pagingRs.getData();
        List<TblPostResponse> caseResponses = Utilities.copyProperties(datas, TblPostResponse.class);
        pagingRs.setData(caseResponses);
        pagingRs.setTotalRecords(caseResponses.size());
        return pagingRs;
    }

    @Override
    public TblPostResponse insert(TblPostCreateRequest postRequest, List<TblMediaCreateRequest> mediaRequest) throws IOException {
        UserResponse user = getUser();
        TblPost post = Utilities.copyProperties(postRequest, TblPost.class);
        post.setUserId(user.getId());
        post.setStatus(ContentStatus.ACTIVE);
        postRepository.save(post);

        if (ObjectUtils.isEmpty(mediaRequest)) {
            return Utilities.copyProperties(post, TblPostResponse.class);
        }
        List<TblMedia> media = Utilities.copyProperties(mediaRequest, TblMedia.class);
        media.forEach(m -> {
            m.setPostId(post.getId());
            m.setStatus(ContentStatus.ACTIVE);
        });
        mediaRepository.saveAll(media);
        return Utilities.copyProperties(post, TblPostResponse.class);
    }

    private UserResponse getUser() throws IOException {
        Call<PagingResponse> call = userApiService.getUser("Bearer " + BearerContextHolder.getContext().getToken(), BearerContextHolder.getContext().getMasterAccount());
        Response<PagingResponse> response = call.execute();
        List<PagingResponse> pagingResponse = (List<PagingResponse>) response.body().getData();
        List<UserResponse> lsUser = Utilities.copyProperties(pagingResponse, UserResponse.class);
        return lsUser.get(0);
    }
}
