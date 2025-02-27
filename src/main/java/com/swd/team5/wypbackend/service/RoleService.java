package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.RoleRequest;
import com.swd.team5.wypbackend.dto.response.RoleResponse;
import com.swd.team5.wypbackend.entity.Permission;
import com.swd.team5.wypbackend.entity.Role;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.RoleMapper;
import com.swd.team5.wypbackend.repository.PermissionRepository;
import com.swd.team5.wypbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        if(roleRepository.existsById(request.getName())){
            throw new AppException(ErrorCode.EXISTED_ROLE);
        }
        Role role = roleMapper.toRole(request);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toResponse).toList();
    }


    public String delete(String roleName) {
        Role role = roleRepository.findById(roleName)
                        .orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE));
        roleRepository.delete(role);
        return "Delete successfully 👙";
    }

    public RoleResponse update(String roleName, RoleRequest request) {
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE));

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        roleMapper.updateRole(role, request);
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toResponse(roleRepository.save(role));
    }
}
