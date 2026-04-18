package com.daniyar.ecommerce.domain.payment.controller;

import com.daniyar.ecommerce.domain.payment.entity.Payment;
import com.daniyar.ecommerce.domain.payment.service.PaymentService;
import com.daniyar.ecommerce.domain.payment.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final StripeService stripeService;

    // Process payment
    @PostMapping("/process/{orderId}")
    public Payment processPayment(@PathVariable Long orderId, @RequestParam BigDecimal amount) {
        return paymentService.processPayment(orderId, amount);
    }

    @PutMapping("/{paymentId}/cancel")
    public Payment cancelPayment(@PathVariable Long paymentId) {
        return paymentService.cancelPayment(paymentId);  // Call service to cancel the payment
    }


    // Endpoint to create a payment intent (for front-end integration)
    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestParam BigDecimal amount, @RequestParam String currency) throws StripeException {
        return stripeService.createPaymentIntent(amount, currency);  // Create a PaymentIntent with Stripe
    }

    // Endpoint for creating a one-time charge (for simple payments)
    @PostMapping("/create-charge")
    public Charge createCharge(@RequestParam BigDecimal amount, @RequestParam String currency, @RequestParam String source) throws StripeException {
        return stripeService.createCharge(amount, currency, source);  // Create a charge with Stripe
    }
}