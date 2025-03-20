package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ApiResponse<String> fileUpload(@RequestParam MultipartFile file){
        return ApiResponse.<String>builder()
                .result(cloudinaryService.upload(file))
                .build();
    }
}
