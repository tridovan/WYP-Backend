package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.CartCreateRequest;
import com.swd.team5.wypbackend.dto.response.CartResponse;
import com.swd.team5.wypbackend.entity.Cart;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.CartMapper;
import com.swd.team5.wypbackend.repository.CartRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;

    public CartResponse create(CartCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Cart cart = cartMapper.toEntity(request);
        cart.setUser(user);
        cart = cartRepository.save(cart);

        return cartMapper.toResponse(cart);
    }

    public List<CartResponse> getAllByUserId(String userId) {
        List<Cart> carts = cartRepository.findByUser_Id(userId);
        return carts.stream()
                .map(cartMapper::toResponse)
                .toList();
    }

    public CartResponse getById(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        return cartMapper.toResponse(cart);
    }

    public void delete(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        cartRepository.delete(cart);
    }
}
