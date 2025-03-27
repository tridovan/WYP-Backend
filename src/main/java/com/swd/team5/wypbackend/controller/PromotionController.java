package com.swd.team5.wypbackend.controller;

    import com.swd.team5.wypbackend.dto.request.PromotionCreateRequest;
    import com.swd.team5.wypbackend.dto.response.ApiResponse;
    import com.swd.team5.wypbackend.dto.response.PromotionResponse;
    import com.swd.team5.wypbackend.service.PromotionService;
    import io.swagger.v3.oas.annotations.Operation;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/promotions")
    @RequiredArgsConstructor
    public class PromotionController {
        private final PromotionService promotionService;

        @GetMapping
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(description = "Get all promotions - Admin only")
        public ApiResponse<List<PromotionResponse>> getAllPromotions() {
            return ApiResponse.<List<PromotionResponse>>builder()
                    .result(promotionService.findAll())
                    .build();
        }

        @PostMapping("/create")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(description = "Create new promotion - Admin only")
        public ApiResponse<PromotionResponse> createPromotion(@RequestBody PromotionCreateRequest request) {
            return ApiResponse.<PromotionResponse>builder()
                    .result(promotionService.create(request))
                    .build();
        }

        @DeleteMapping("/delete/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(description = "Delete promotion - Admin only")
        public ApiResponse<Void> deletePromotion(@PathVariable Long id) {
            promotionService.delete(id);
            return ApiResponse.<Void>builder()
                    .message("Deleted successfully")
                    .build();
        }
    }