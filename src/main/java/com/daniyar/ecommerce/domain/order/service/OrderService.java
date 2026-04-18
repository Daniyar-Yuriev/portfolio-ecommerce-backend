package com.daniyar.ecommerce.domain.order.service;

import com.daniyar.ecommerce.domain.cart.entity.Cart;
import com.daniyar.ecommerce.domain.cart.entity.CartItem;
import com.daniyar.ecommerce.domain.cart.repository.CartItemRepository;
import com.daniyar.ecommerce.domain.cart.repository.CartRepository;
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


    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public Order getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Order not found for customer"));
    }


    // Create order from cart items
    @Transactional
    public Order createOrder(Long customerId) {

        // Retrieve cart for the customer
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
//        order.setCustomerId(customerId);

        // Convert CartItems to OrderItems
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());

            order.getOrderItems().add(orderItem);
        }

        // Calculate total amount of the order
        order.calculateTotalAmount();

        // Save the order
        orderRepository.save(order);

        // Optionally, remove items from cart after the order is placed
        cartItemRepository.deleteAll(cart.getCartItems());

        return order;
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);  // Update the status
        return orderRepository.save(order);  // Save the updated order
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Only orders in PENDING status can be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED); // Update status to CANCELLED
        return orderRepository.save(order);  // Save the updated order
    }
}