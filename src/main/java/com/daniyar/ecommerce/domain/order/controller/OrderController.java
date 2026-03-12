package com.daniyar.ecommerce.domain.order.controller;

import com.daniyar.ecommerce.domain.order.dto.OrderRequest;
import com.daniyar.ecommerce.domain.order.entity.Order;
import com.daniyar.ecommerce.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // create Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        // get customerId, productIds, quantities from request and create OrderRequest
        Order order = orderService.createOrder(orderRequest.getCustomerId(),
                orderRequest.getProductIds(),
                orderRequest.getQuantities());
        return ResponseEntity.ok(order);
    }
}