package com.daniyar.ecommerce.domain.payment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    // Initialize Stripe API with secret key
    public StripeService() {
        Stripe.apiKey = stripeSecretKey;  // Set the Stripe secret key
    }

    // Create a PaymentIntent and confirm the payment
    public PaymentIntent createPaymentIntent(BigDecimal amount, String currency) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.multiply(new BigDecimal(100)).longValue())  // Stripe expects the amount in cents
                .setCurrency(currency)
                .build();

        return PaymentIntent.create(params);  // Create a PaymentIntent with the specified parameters
    }

    // Create a charge (for one-time payments)
    public Charge createCharge(BigDecimal amount, String currency, String source) throws StripeException {
        return Charge.create(
                Map.of(
                        "amount", amount.multiply(new BigDecimal(100)).longValue(),  // Stripe expects the amount in cents
                        "currency", currency,
                        "source", source  // Card or payment source ID from Stripe.js
                )
        );
    }
}