package com.daniyar.ecommerce.domain.category.dto;

public record CategoryResponse(
        Long id,
        String name,
        Long parentId
) {}