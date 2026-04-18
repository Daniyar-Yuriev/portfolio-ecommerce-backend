package com.daniyar.ecommerce.domain.order.entity;

public enum OrderStatus {
    PENDING, // Order placed but not yet confirmed
    CONFIRMED, // Order confirmed by the system
    SHIPPED, // Order shipped
    DELIVERED, // Order delivered to the customer
    CANCELLED // Order cancelled
}