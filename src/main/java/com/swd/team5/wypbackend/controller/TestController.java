package com.swd.team5.wypbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.PageResponse;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.service.CloudinaryService;
import com.swd.team5.wypbackend.service.ProductService;
import com.swd.team5.wypbackend.service.UserSerivce;
import jakarta.validation.Valid;
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

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<String> fileUpload(@RequestParam MultipartFile file){
        return ApiResponse.<String>builder()
                .result(cloudinaryService.upload(file))
                .build();
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }


    @GetMapping("/all")
    public ApiResponse<?> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                @RequestParam(required = false) String... sorts){
        return ApiResponse.builder()
                .result(userSerivce.getAllUserSortBy(pageNo, pageSize, sorts))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<?> getAll(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                 @RequestParam(defaultValue = "20", required = false) int pageSize,
                                 @RequestParam(required = false) String brand,
                                 @RequestParam(required = false) String sort,
                                 @RequestParam(required = false) String... searches) {
        return ApiResponse.builder()
                .result(productService.advanceSearchByCriteria(pageNo, pageSize, brand, sort, searches))
                .build();
    }

    @PostMapping(value = "/create")
    public ApiResponse<ProductResponse> create(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "json") String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductCreateRequest request = null;
        try {
            request = objectMapper.readValue(json, ProductCreateRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request, file))
                .build();
    }

}
