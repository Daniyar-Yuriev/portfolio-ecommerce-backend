package com.daniyar.ecommerce.domain.cart.repository;


import com.daniyar.ecommerce.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByProductIdAndCartId(Long productId, Long cartId);
}