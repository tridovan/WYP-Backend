package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.RoleRequest;
import com.swd.team5.wypbackend.dto.response.RoleResponse;
import com.swd.team5.wypbackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    void updateRole(@MappingTarget Role role, RoleRequest request);
}
