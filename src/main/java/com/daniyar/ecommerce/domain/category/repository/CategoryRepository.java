package com.daniyar.ecommerce.domain.category.repository;

import com.daniyar.ecommerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}