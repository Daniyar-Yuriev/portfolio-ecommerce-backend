package com.daniyar.ecommerce.domain.order.entity;

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
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // ordered product

    private Integer quantity;  // ordered quantity
    private BigDecimal price;  // ordered product price

    // total price
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }
}