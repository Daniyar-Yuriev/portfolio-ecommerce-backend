package com.daniyar.ecommerce.domain.product.repository;

import com.daniyar.ecommerce.domain.product.dto.ProductSearchRequest;
import com.daniyar.ecommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> search(ProductSearchRequest request, Pageable pageable);
}
