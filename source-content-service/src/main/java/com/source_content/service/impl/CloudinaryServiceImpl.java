package com.source_content.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.source_content.domain.media.CloudinaryUploadResponse;
import com.source_content.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public List<CloudinaryUploadResponse> upload(MultipartFile[] files) {
        List<CloudinaryUploadResponse> responses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (MultipartFile file : files) {
            try {
                Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
                responses.add(mapper.convertValue(data, CloudinaryUploadResponse.class));
            } catch (IOException io) {
                throw new RuntimeException("Image upload fail");
            }
        }
        return responses;
    }

}
