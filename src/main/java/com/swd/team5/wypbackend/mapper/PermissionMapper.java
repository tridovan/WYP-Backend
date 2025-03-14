package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.PermissionRequest;
import com.swd.team5.wypbackend.dto.response.PermissionResponse;
import com.swd.team5.wypbackend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);

}
