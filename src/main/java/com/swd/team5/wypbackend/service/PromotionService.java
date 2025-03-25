package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.PromotionCreateRequest;
import com.swd.team5.wypbackend.dto.response.PromotionResponse;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.entity.Promotion;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.PromotionMapper;
import com.swd.team5.wypbackend.repository.ProductRepository;
import com.swd.team5.wypbackend.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;
    private final PromotionMapper promotionMapper;

    public List<PromotionResponse> findAll() {
        return promotionRepository.findAll().stream()
                .map(promotionMapper::toResponse)
                .toList();
    }

    @Transactional
    public PromotionResponse create(PromotionCreateRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Promotion promotion = promotionMapper.toEntity(request);
        promotion.setProduct(product);

        return promotionMapper.toResponse(promotionRepository.save(promotion));
    }

    @Transactional
    public void delete(Long id) {
        if (!promotionRepository.existsById(id)) {
            throw new AppException(ErrorCode.PROMOTION_NOT_FOUND);
        }
        promotionRepository.deleteById(id);
    }
}