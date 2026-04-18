package com.daniyar.ecommerce.domain.payment.entity;

import com.daniyar.ecommerce.domain.order.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;  // The order this payment corresponds to

    private BigDecimal amount;  // The amount paid
    private LocalDateTime paymentDate;  // The date when payment was made

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;  // Payment status (e.g., PENDING, COMPLETED, FAILED)

    // Set payment date before persist
    @PrePersist
    public void prePersist() {
        this.paymentDate = LocalDateTime.now();
    }
}