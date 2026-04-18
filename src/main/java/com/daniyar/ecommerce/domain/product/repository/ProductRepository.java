package com.daniyar.ecommerce.domain.product.repository;

import com.daniyar.ecommerce.domain.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(@Param("id") Long id);
//    Page<Product> findByNameContaining(String name);
//    Page<Product> findByPriceGreaterThan(BigDecimal price);
//    Page<Product> findByStockLessThanEqual(Integer stock);

}