package com.daniyar.ecommerce.domain.order.controller;

import com.daniyar.ecommerce.domain.order.dto.OrderRequest;
import com.daniyar.ecommerce.domain.order.entity.Order;
import com.daniyar.ecommerce.domain.order.entity.OrderStatus;
import com.daniyar.ecommerce.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // Place an order (convert cart to order)
    @PostMapping("/create/{customerId}")
    public Order createOrder(@PathVariable Long customerId) {
        return orderService.createOrder(customerId);
    }

    @PutMapping("/{orderId}/status")
    public Order changeOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);  // Call service to update the order status
    }

    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);  // Call service to cancel the order
    }

    @GetMapping("/customer/{customerId}")
    public Order getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);  // Fetch orders by customer ID
    }
}