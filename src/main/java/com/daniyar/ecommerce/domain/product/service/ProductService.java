package com.daniyar.ecommerce.domain.product.service;


import com.daniyar.ecommerce.domain.product.dto.ProductCreateRequest;
import com.daniyar.ecommerce.domain.product.dto.ProductResponse;
import com.daniyar.ecommerce.domain.product.dto.ProductSearchRequest;
import com.daniyar.ecommerce.domain.product.entity.Product;
import com.daniyar.ecommerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponse> getProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(p ->
                new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStock()
                )
        );
    }

    public Page<ProductResponse> getProductsByCategory(Long categoryId, Pageable pageable) {

        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);

        return products.map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        ));
    }
    public ProductResponse createProduct(ProductCreateRequest request) {

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .build();

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock()
        );
    }


    public ProductResponse updateProduct(Long productId, ProductCreateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());

        productRepository.save(product);

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }


    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Page<ProductResponse> searchProducts(ProductSearchRequest request, Pageable pageable) {
        Page<Product> products = productRepository.search(request, pageable);

        return products.map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        ));
    }
}