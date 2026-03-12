package com.daniyar.ecommerce.domain.cart.repository;

import com.daniyar.ecommerce.domain.cart.entity.Cart;
import com.daniyar.ecommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long customerId);

}