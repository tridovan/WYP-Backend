package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.request.ProductUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(description = "Tạo sản phẩm")
    @PostMapping("/create")
    public ApiResponse<ProductResponse> create(@RequestPart MultipartFile image, @Valid @RequestPart ProductCreateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request, image))
                .build();
    }

    @Operation(description = "lấy tất cả sản phẩm, không phân trang, không sort. nên lấy cái dưới")
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
    @Operation(description = "tìm sản phẩm theo id")
    public ApiResponse<ProductResponse> getById(@PathVariable Long productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(productId))
                .build();
    }

    @Operation(description = "cập nhật sản phẩm")
    @PutMapping("/update/{productId}")
    public ApiResponse<ProductResponse> update(@PathVariable Long productId,
                                               @Valid @RequestPart(required = false) ProductUpdateRequest request,
                                               @RequestPart(required = false) MultipartFile image) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.update(productId, request, image))
                .build();
    }

    @DeleteMapping("/delete/{productId}")
    public ApiResponse<Void> delete(@PathVariable Long productId) {
        productService.delete(productId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @Operation(description = "tìm kiếm sản phẩm theo nhiều field có phân trang + sắp xếp theo thuộc tính| " +
            "| "+
            "cấu trúc các field sort field:asc (asc là tăng, desc là giảm)| " +
            "cấu trúc các field searchs field:value (: là bằng > là lớn hơn < là bé hơn)| " +
            "ví dụ: http://localhost:8080/products/search?pageNo=1&pageSize=5&sort=id:&searches=name:adidas&searches=price>15 "+
            "nghĩa là lấy tìm toàn bộ sản phẩm tên là adidas, có giá lớn hơn 15, sort theo id")
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

    @Operation(description = "lấy tất cả sản phẩm có phân trang + sắp xếp theo thuộc tính " +
            "cấu trúc các field sort field:asc (asc là tăng, desc là giảm) " +
            "http://localhost:8080/products/list?pageNo=1&pageSize=10&sorts=id:desc ")
    @GetMapping("/list")  public ApiResponse<?> getAllProducts(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                           @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                           @RequestParam(required = false) String... sorts){
        return ApiResponse.builder()
                .result(productService.getAllProductsSortBy(pageNo, pageSize, sorts))
                .build();
    }
}
