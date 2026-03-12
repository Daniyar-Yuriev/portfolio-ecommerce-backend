package com.daniyar.ecommerce.domain.category.controller;

import com.daniyar.ecommerce.domain.category.dto.CategoryCreateRequest;
import com.daniyar.ecommerce.domain.category.dto.CategoryResponse;
import com.daniyar.ecommerce.domain.category.service.CategoryService;
import com.daniyar.ecommerce.domain.product.dto.ProductResponse;
import com.daniyar.ecommerce.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<Page<CategoryResponse>> getCategories(Pageable pageable) {
        Page<CategoryResponse> categories= categoryService.getCategories(pageable);
        return ApiResponse.<Page<CategoryResponse>>builder()
                .status(200)
                .message("success")
                .data(categories)
                .build();
    }
    @PostMapping
    public void create(@RequestBody CategoryCreateRequest request) {
        categoryService.create(request);
    }
}