package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.CartItemCreateRequest;
import com.swd.team5.wypbackend.dto.request.CartItemUpdateRequest;
import com.swd.team5.wypbackend.dto.response.CartItemResponse;
import com.swd.team5.wypbackend.entity.Cart;
import com.swd.team5.wypbackend.entity.CartItem;
import com.swd.team5.wypbackend.entity.Customization;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.CartItemMapper;
import com.swd.team5.wypbackend.repository.CartItemRepository;
import com.swd.team5.wypbackend.repository.CartRepository;
import com.swd.team5.wypbackend.repository.CustomizationRepository;
import com.swd.team5.wypbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomizationRepository customizationRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository,
                           ProductRepository productRepository, CustomizationRepository customizationRepository,
                           CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customizationRepository = customizationRepository;
        this.cartItemMapper = cartItemMapper;
    }

    public CartItemResponse create(CartItemCreateRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        Product product = productRepository.findById(Long.parseLong(request.getProductId()))
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Customization customization = null;
        if (request.getCustomizationId() != null) {
            customization = customizationRepository.findById(Long.parseLong(request.getCustomizationId()))
                    .orElseThrow(() -> new AppException(ErrorCode.CUSTOMIZATION_NOT_EXISTED));
        }

        CartItem cartItem = cartItemMapper.toEntity(request);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCustomization(customization);
        cartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.toResponse(cartItem);
    }

    public CartItemResponse update(String cartItemId, CartItemUpdateRequest request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_EXISTED));

        cartItemMapper.update(cartItem, request);
        cartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.toResponse(cartItem);
    }

    public void delete(String cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_EXISTED));
        cartItemRepository.delete(cartItem);
    }

    public List<CartItemResponse> getAllByCartId(String cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCart_Id(cartId);
        return cartItems.stream()
                .map(cartItemMapper::toResponse)
                .toList();
    }
}
