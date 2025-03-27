package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.BrandCreateRequest;
import com.swd.team5.wypbackend.dto.response.BrandResponse;
import com.swd.team5.wypbackend.entity.Brand;
import com.swd.team5.wypbackend.mapper.BrandMapper;
import com.swd.team5.wypbackend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final CloudinaryService cloudinaryService;

    @Transactional
    public BrandResponse createBrand(BrandCreateRequest request, MultipartFile image) {
        if (brandRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Brand with name '" + request.getName() + "' already exists");
        }
        Brand brand = brandMapper.toEntity(request);
        brand.setImage(cloudinaryService.upload(image));
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toResponse(savedBrand);
    }

    public BrandResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand with id '" + id + "' not found"));
        return brandMapper.toResponse(brand);
    }

    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BrandResponse updateBrand(Long id, BrandCreateRequest request, MultipartFile image) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand with id '" + id + "' not found"));

        if (!brand.getName().equals(request.getName()) && brandRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Brand with name '" + request.getName() + "' already exists");
        }

        brand.setName(request.getName());
        brand.setImage(cloudinaryService.upload(image));
        Brand updatedBrand = brandRepository.save(brand);
        return brandMapper.toResponse(updatedBrand);
    }

    @Transactional
    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new IllegalArgumentException("Brand with id '" + id + "' not found");
        }
        brandRepository.deleteById(id);
    }
}
