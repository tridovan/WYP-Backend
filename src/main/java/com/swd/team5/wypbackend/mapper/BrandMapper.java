package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.BrandCreateRequest;
import com.swd.team5.wypbackend.dto.response.BrandResponse;
import com.swd.team5.wypbackend.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    Brand toEntity(BrandCreateRequest request);

    BrandResponse toResponse(Brand brand);
}
