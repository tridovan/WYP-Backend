package com.swd.team5.wypbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String upload(MultipartFile file){
        try {
            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "image"
            ));
            return result.get("url").toString();
        } catch (IOException e) {
            throw new AppException(ErrorCode.FILE_ERROR);
        }
    }

}
