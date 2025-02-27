package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.PermissionRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.PermissionResponse;
import com.swd.team5.wypbackend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @PutMapping("/{permissionName}")
    public ApiResponse<PermissionResponse> getAll(@PathVariable String permissionName, @RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.update(permissionName, request))
                .build();
    }

    @DeleteMapping("/{permissionName}")
    public ApiResponse<?> delete(@PathVariable String permissionName){
        return ApiResponse.<List<PermissionResponse>>builder()
                .message(permissionService.delete(permissionName))
                .build();
    }
}
