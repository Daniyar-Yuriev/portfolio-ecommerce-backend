package com.daniyar.ecommerce.domain.order.entity;

import com.daniyar.ecommerce.domain.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer ID that owns the order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;  // Many orders can belong to one customer

    private LocalDateTime orderDate;  // Order creation date

    private BigDecimal totalAmount;  // Total amount for the order

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // Order status (e.g., PENDING, CONFIRMED)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();  // List of items in the order

    // Calculate the total amount of the order based on the order items
    public void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Set order date before persist
    @PrePersist
    public void prePersist() {
        this.orderDate = LocalDateTime.now();
    }
}