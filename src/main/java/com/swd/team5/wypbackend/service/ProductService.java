package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.request.ProductUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.entity.Brand;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.ProductMapper;
import com.swd.team5.wypbackend.repository.BrandRepository;
import com.swd.team5.wypbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.EXISTED_PRODUCT_NAME);
        }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        Product product = productMapper.toProduct(request);
        product.setBrand(brand);

        return productMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse update(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productMapper.updateProduct(product, request);

        if (request.getBrandId() != null) {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
            product.setBrand(brand);
        }

        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(productId);
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toResponse(product);
    }
}
