package com.daniyar.ecommerce.domain.payment.service;

import com.daniyar.ecommerce.domain.order.entity.Order;
import com.daniyar.ecommerce.domain.order.entity.OrderItem;
import com.daniyar.ecommerce.domain.order.entity.OrderStatus;
import com.daniyar.ecommerce.domain.order.repository.OrderRepository;
import com.daniyar.ecommerce.domain.payment.entity.Payment;
import com.daniyar.ecommerce.domain.payment.entity.PaymentStatus;
import com.daniyar.ecommerce.domain.payment.repository.PaymentRepository;
import com.daniyar.ecommerce.domain.product.entity.Product;
import com.daniyar.ecommerce.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;

    // Process payment
    @Transactional
    public Payment processPayment(Long orderId, BigDecimal amount) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new RuntimeException("Order is not confirmed yet");
        }

        // Simulate the payment process here
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.COMPLETED);

        // Save the payment record
        paymentRepository.save(payment);

        // After successful payment, change order status to DELIVERED
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);

        // Deduct stock for each product in the order (Optimistic Locking in action)
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = orderItem.getProduct();
            if (product.getStock() < orderItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);  // This will use optimistic locking to prevent concurrency issues
        }

        return payment;
    }

    // Cancel payment (only if payment status is PENDING)
    @Transactional
    public Payment cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Only payments in PENDING status can be cancelled");
        }

        payment.setStatus(PaymentStatus.FAILED);  // Change status to FAILED
        paymentRepository.save(payment);  // Save the updated payment

        // Reverse the stock deduction for the order items if payment is cancelled
        for (OrderItem orderItem : payment.getOrder().getOrderItems()) {
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() + orderItem.getQuantity());  // Restore stock
            productRepository.save(product);  // Save the updated product stock
        }

        return payment;
    }

    // Simulate a payment process (for testing purposes)
    private boolean simulatePayment(BigDecimal amount) {
        // Simulate a random payment success or failure
        return Math.random() > 0.5;
    }
}