package com.source_content.service;

import com.source_content.domain.media.CloudinaryUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinaryService {

    List<CloudinaryUploadResponse> upload(MultipartFile[] files) throws IOException;
}
