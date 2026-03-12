package com.daniyar.ecommerce.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public record ProductSearchRequest(
        String keyword,
        Long categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}