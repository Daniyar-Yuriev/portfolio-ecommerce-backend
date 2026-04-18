package com.daniyar.ecommerce.domain.payment.entity;

public enum PaymentStatus {
    PENDING,  // Payment is still processing
    COMPLETED, // Payment has been successfully completed
    FAILED     // Payment failed
}