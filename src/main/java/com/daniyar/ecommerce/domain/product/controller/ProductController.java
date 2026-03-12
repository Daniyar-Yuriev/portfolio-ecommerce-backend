package com.daniyar.ecommerce.domain.product.controller;

import com.daniyar.ecommerce.global.response.ApiResponse;
import com.daniyar.ecommerce.domain.product.dto.ProductCreateRequest;
import com.daniyar.ecommerce.domain.product.dto.ProductResponse;
import com.daniyar.ecommerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false) Long categoryId, // 카테고리 ID가 있을 수도 있고, 없을 수도 있음
            Pageable pageable) {  // Page 요청 (Pageable)

        if (categoryId != null) {
            return productService.getProductsByCategory(categoryId, pageable);
        } else {
            return productService.getProducts(pageable);
        }
    }

    @PostMapping
    public ProductResponse createProduct(
            @RequestBody ProductCreateRequest request
    ) {
        return productService.createProduct(request);
    }
}