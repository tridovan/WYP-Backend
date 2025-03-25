package com.swd.team5.wypbackend.controller;


import com.swd.team5.wypbackend.dto.request.RoleRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.RoleResponse;
import com.swd.team5.wypbackend.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    @Operation(description = "Tạo mới role, quyền admin")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    @Operation(description = "Lấy tất cả role, quyền admin")
    public ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();

    }

    @PutMapping("/{roleName}")
    @Operation(description = "cập nhật role, quyền admin")
    public ApiResponse<RoleResponse> update(@PathVariable String roleName, @RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.update(roleName, request))
                .build();
    }


    @DeleteMapping("/{roleName}")
    @Operation(description = "Xóa role, quyền admin")
    public ApiResponse<Void> delete(@PathVariable String roleName){
        return ApiResponse.<Void>builder()
                .message(roleService.delete(roleName))
                .build();
    }
}
