package com.daniyar.ecommerce.domain.cart.controller;

import com.daniyar.ecommerce.domain.cart.dto.CartItemRequest;
import com.daniyar.ecommerce.domain.cart.entity.Cart;
import com.daniyar.ecommerce.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;


    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable Long customerId) {
        return cartService.getCartByCustomer(customerId);
    }


    @PostMapping("/{customerId}/add")
    public ResponseEntity<String> addItemToCart(@PathVariable Long customerId,
                                                @RequestBody CartItemRequest cartItemRequest) {
        cartService.addItemToCart(customerId, cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        return ResponseEntity.ok("Item added to cart");
    }


    @DeleteMapping("/{customerId}/remove/{productId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long customerId,
                                                     @PathVariable Long productId) {
        cartService.removeItemFromCart(customerId, productId);
        return ResponseEntity.ok("Item removed from cart");
    }
}