package com.daniyar.ecommerce.domain.product.repository;

import com.daniyar.ecommerce.domain.product.dto.ProductSearchRequest;
import com.daniyar.ecommerce.domain.product.entity.Product;
import com.daniyar.ecommerce.domain.product.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Product> search(ProductSearchRequest request, Pageable pageable) {


        QProduct product = QProduct.product;

        // BooleanBuilder: dynamic query condition add
        BooleanBuilder builder = new BooleanBuilder();

        // 조건이 존재하면 추가
        if (request.keyword() != null) {
            builder.and(product.name.containsIgnoreCase(request.keyword()));
        }

        if (request.categoryId() != null) {
            builder.and(product.category.id.eq(request.categoryId()));
        }

        if (request.minPrice() != null) {
            builder.and(product.price.goe(request.minPrice()));
        }

        if (request.maxPrice() != null) {
            builder.and(product.price.loe(request.maxPrice()));
        }



        List<Product> products = queryFactory
                .selectFrom(product)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = queryFactory
                .selectFrom(product)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(products, pageable, total);
    }
}