package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.request.ProductUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAll())
                .build();
    }
    @GetMapping("/customer/{productId}")
    @Operation(description = "lay san pham bang id http://localhost:8080/products/customer/{productId}")
    public ApiResponse<ProductResponse> getByIdForCustomer(@PathVariable Long productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(productId))
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(productId))
                .build();
    }

    @PutMapping("/update/{productId}")
    public ApiResponse<ProductResponse> update(@PathVariable Long productId,
                                               @Valid @RequestBody ProductUpdateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.update(productId, request))
                .build();
    }

    @DeleteMapping("/delete/{productId}")
    public ApiResponse<Void> delete(@PathVariable Long productId) {
        productService.delete(productId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
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
}
