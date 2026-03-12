package com.daniyar.ecommerce.domain.category.service;

import com.daniyar.ecommerce.domain.category.dto.CategoryCreateRequest;
import com.daniyar.ecommerce.domain.category.dto.CategoryResponse;
import com.daniyar.ecommerce.domain.category.entity.Category;
import com.daniyar.ecommerce.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryResponse> getCategories(Pageable pageable) {

        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> new CategoryResponse(category.getId(), category.getName(),category.getParent() != null ? category.getParent().getId() : null));
    }
    public void create(CategoryCreateRequest request) {

        Category parent = null;

        if (request.parentId() != null) {
            parent = categoryRepository.findById(request.parentId())
                    .orElseThrow();
        }

        Category category = Category.builder()
                .name(request.name())
                .parent(parent)
                .build();

        categoryRepository.save(category);
    }
}