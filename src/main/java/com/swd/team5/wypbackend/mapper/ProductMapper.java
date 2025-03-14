package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.request.ProductUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toProduct(ProductCreateRequest request);

    @Mapping(target = "brandName", expression = "java(product.getBrand() != null ? product.getBrand().getName() : null)")
    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
