package com.daniyar.ecommerce.domain.product.repository;

import com.daniyar.ecommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
//    Page<Product> findByNameContaining(String name);
//    Page<Product> findByPriceGreaterThan(BigDecimal price);
//    Page<Product> findByStockLessThanEqual(Integer stock);

}