package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.BrandCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.BrandResponse;
import com.swd.team5.wypbackend.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/create")
    @Operation(description = "Tạo mới một thương hiệu - http://localhost:8080/brands/create")
    public ApiResponse<BrandResponse> create(@Valid @RequestPart BrandCreateRequest request, @RequestPart MultipartFile image) {
        return ApiResponse.<BrandResponse>builder()
                .result(brandService.createBrand(request, image))
                .message("Brand created successfully")
                .build();
    }

    @GetMapping
    @Operation(description = "Lấy tất cả thương hiệu - http://localhost:8080/brands")
    public ApiResponse<List<BrandResponse>> getAll() {
        return ApiResponse.<List<BrandResponse>>builder()
                .result(brandService.getAllBrands())
                .message("Brands retrieved successfully")
                .build();
    }

    @GetMapping("/{brandId}")
    @Operation(description = "Lấy thương hiệu theo ID - http://localhost:8080/brands/{brandId}")
    public ApiResponse<BrandResponse> getById(@PathVariable Long brandId) {
        return ApiResponse.<BrandResponse>builder()
                .result(brandService.getBrandById(brandId))
                .message("Brand retrieved successfully")
                .build();
    }

    @PutMapping("/update/{brandId}")
    @Operation(description = "Cập nhật thương hiệu - http://localhost:8080/brands/update/{brandId}")
    public ApiResponse<BrandResponse> update(@PathVariable Long brandId,
                                             @Valid @RequestPart BrandCreateRequest request,
                                             @RequestPart(required = false) MultipartFile image) {
        return ApiResponse.<BrandResponse>builder()
                .result(brandService.updateBrand(brandId, request, image))
                .message("Brand updated successfully")
                .build();
    }

    @DeleteMapping("/delete/{brandId}")
    @Operation(description = "Xóa thương hiệu - http://localhost:8080/brands/delete/{brandId}")
    public ApiResponse<Void> delete(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return ApiResponse.<Void>builder()
                .message("Brand deleted successfully")
                .build();
    }
}
