package com.daniyar.ecommerce.domain.order.service;

import com.daniyar.ecommerce.domain.customer.entity.Customer;
import com.daniyar.ecommerce.domain.customer.repository.CustomerRepository;
import com.daniyar.ecommerce.domain.order.entity.Order;
import com.daniyar.ecommerce.domain.order.entity.OrderItem;
import com.daniyar.ecommerce.domain.order.entity.OrderStatus;
import com.daniyar.ecommerce.domain.order.repository.OrderItemRepository;
import com.daniyar.ecommerce.domain.order.repository.OrderRepository;
import com.daniyar.ecommerce.domain.product.entity.Product;
import com.daniyar.ecommerce.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    // 주문 생성
    @Transactional
    public Order createOrder(Long customerId, List<Long> productIds, List<Integer> quantities) {

        // 고객 확인
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.ORDERED);

        // 주문 항목 추가
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantities.get(i));
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        // 주문 항목도 저장
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }

        return order;
    }
}