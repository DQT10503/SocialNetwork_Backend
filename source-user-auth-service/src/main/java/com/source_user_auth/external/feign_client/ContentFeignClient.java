package com.source_user_auth.external.feign_client;

import com.source_user_auth.config.FeignConfig;
import com.source_user_auth.domain.CloudinaryUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient (
        name = "ContentClient",
        url = "${service.content.base-url}",
        configuration = FeignConfig.class
)
public interface ContentFeignClient {

    @PostMapping("/post/upload")
    List<CloudinaryUploadResponse> upload(@RequestPart("files") MultipartFile[] files);
}
