package com.daniyar.ecommerce.domain.cart.service;

import com.daniyar.ecommerce.domain.cart.entity.Cart;
import com.daniyar.ecommerce.domain.cart.entity.CartItem;
import com.daniyar.ecommerce.domain.cart.repository.CartItemRepository;
import com.daniyar.ecommerce.domain.cart.repository.CartRepository;
import com.daniyar.ecommerce.domain.product.entity.Product;
import com.daniyar.ecommerce.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;


    public Cart getCartByCustomer(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer"));
    }


    @Transactional
    public void addItemToCart(Long customerId, Long productId, Integer quantity) {
        Cart cart = getCartByCustomer(customerId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice());
            cartItemRepository.save(cartItem);
        }
    }


    @Transactional
    public void removeItemFromCart(Long customerId, Long productId) {
        Cart cart = getCartByCustomer(customerId);
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartItemRepository.deleteByProductIdAndCartId(productId, cart.getId());
    }

    @Transactional
    public Cart getOrCreateCart(Long customerId) {

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setCustomerId(customerId);
                    return cartRepository.save(cart);
                });
    }

    // Add product to cart
    @Transactional
    public void addProduct(Long customerId, Long productId, Integer quantity) {

        Cart cart = getOrCreateCart(customerId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);
    }

}