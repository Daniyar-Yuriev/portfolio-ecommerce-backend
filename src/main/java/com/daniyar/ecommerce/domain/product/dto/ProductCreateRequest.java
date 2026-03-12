package com.daniyar.ecommerce.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateRequest(

        @NotBlank
        String name,

        String description,

        @NotNull
        BigDecimal price,

        @NotNull
        Integer stock
) {}