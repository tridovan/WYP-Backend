package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.PermissionRequest;
import com.swd.team5.wypbackend.dto.response.PermissionResponse;
import com.swd.team5.wypbackend.entity.Permission;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.PermissionMapper;
import com.swd.team5.wypbackend.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        if(permissionRepository.existsById(request.getName())){
            throw new AppException(ErrorCode.EXISTED_PERMISSION);
        }
        return permissionMapper.toResponse(permissionRepository.save(permissionMapper.toPermission(request)));
    }

    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll().stream().map(permissionMapper::toResponse).toList();
    }

    public PermissionResponse update(String permissionName, PermissionRequest request) {
        Permission permission = permissionRepository.findById(permissionName)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PERMISSION_NAME));
        request.setName(permissionName);
        permissionMapper.updatePermission(permission, request);
        permissionRepository.save(permission);
        return permissionMapper.toResponse(permission);
    }

    public String delete(String permissionName){
        permissionRepository.deleteById(permissionName);
        return "delete successfully";
    }
}
