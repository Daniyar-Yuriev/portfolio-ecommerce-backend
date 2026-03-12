package com.daniyar.ecommerce.domain.cart.entity;

import com.daniyar.ecommerce.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;  // cart

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // products in  cart

    private Integer quantity;  // quantity of product
    private BigDecimal price;

    // total price
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }
}