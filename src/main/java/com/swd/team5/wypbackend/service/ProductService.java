package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.ProductCreateRequest;
import com.swd.team5.wypbackend.dto.request.ProductUpdateRequest;
import com.swd.team5.wypbackend.dto.response.PageResponse;
import com.swd.team5.wypbackend.dto.response.ProductResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.entity.Brand;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.ProductMapper;
import com.swd.team5.wypbackend.repository.BrandRepository;
import com.swd.team5.wypbackend.repository.ProductRepository;
import com.swd.team5.wypbackend.repository.SearchRepository;
import com.swd.team5.wypbackend.repository.criteria.SearchCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SearchRepository searchRepository;

    public ProductResponse create(ProductCreateRequest request, MultipartFile file) {
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.EXISTED_PRODUCT_NAME);
        }

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        Product product = productMapper.toProduct(request);
        product.setBrand(brand);
        product.setImage(cloudinaryService.upload(file));

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

    public PageResponse<?> advanceSearchByCriteria(int pageNo, int pageSize, String brand, String sort, String... searches){

        //get user list
        List<SearchCriteria> criteriaList = new ArrayList<>();
        if(Objects.nonNull(searches)){
            for (String search : searches) {
                    // field:value
                Pattern pattern = Pattern.compile("(\\w+?)(:|>|<)(.*)");
                Matcher matcher = pattern.matcher(search);
                if (matcher.find()) {
                    criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }
        List<Product> products = searchRepository.advanceSearchProduct(pageNo, pageSize, brand, sort, criteriaList);
        List<ProductResponse> responses = products.stream().map(productMapper::toResponse).toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(Math.ceilDiv(responses.size(), pageSize))
                .totalElement(responses.size())
                .sortBy(new String[] {sort})
                .items(responses)
                .build();
    }
}
