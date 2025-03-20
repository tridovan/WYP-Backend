package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.PageResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.service.CloudinaryService;
import com.swd.team5.wypbackend.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserSerivce userSerivce;

    @PostMapping
    public ApiResponse<String> fileUpload(@RequestParam MultipartFile file){
        return ApiResponse.<String>builder()
                .result(cloudinaryService.upload(file))
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<?> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                @RequestParam(required = false) String... sorts){
        return ApiResponse.builder()
                .result(userSerivce.getAllUserSortBy(pageNo, pageSize, sorts))
                .build();
    }
}
