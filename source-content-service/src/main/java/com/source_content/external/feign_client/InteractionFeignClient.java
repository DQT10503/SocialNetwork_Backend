package com.source_content.external.feign_client;

import com.api.framework.domain.PagingRequest;
import com.api.framework.domain.PagingResponse;
import com.source_content.config.FeignConfig;
import com.source_content.domain.interaction_service.BaseInteractionRequest;
import com.source_content.domain.interaction_service.comment.CommentRequest;
import com.source_content.domain.interaction_service.like.LikeRequest;
import com.source_content.domain.interaction_service.share.ShareRequest;
import com.source_content.utils.enummerate.ContentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "InteractionClient",
        url = "${service.interaction.base-url}",
        configuration = FeignConfig.class
)
public interface InteractionFeignClient {

    @GetMapping("/interaction/like")
    PagingResponse getLikes(@SpringQueryMap BaseInteractionRequest request,
                            @RequestParam("offset") int offset,
                            @RequestParam("limit") int limit,
                            @RequestParam("sort") String sort);

    @GetMapping("/interaction/comment")
    PagingResponse getComments(@SpringQueryMap BaseInteractionRequest request,
                               @RequestParam("offset") int offset,
                               @RequestParam("limit") int limit,
                               @RequestParam("sort") String sort);

    @GetMapping("/interaction/share")
    PagingResponse getShares(@SpringQueryMap BaseInteractionRequest request,
                             @RequestParam("offset") int offset,
                             @RequestParam("limit") int limit,
                             @RequestParam("sort") String sort);
}
