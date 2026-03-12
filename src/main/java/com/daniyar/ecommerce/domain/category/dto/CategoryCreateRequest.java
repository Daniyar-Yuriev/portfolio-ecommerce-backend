package com.daniyar.ecommerce.domain.category.dto;

public record CategoryCreateRequest(
        String name,
        Long parentId
) {}