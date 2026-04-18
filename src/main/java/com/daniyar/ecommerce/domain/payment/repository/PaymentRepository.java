package com.daniyar.ecommerce.domain.payment.repository;
import com.daniyar.ecommerce.domain.order.entity.OrderItem;
import com.daniyar.ecommerce.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}