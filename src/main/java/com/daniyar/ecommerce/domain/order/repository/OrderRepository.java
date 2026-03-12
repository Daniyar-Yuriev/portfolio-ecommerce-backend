package com.daniyar.ecommerce.domain.order.repository;
import com.daniyar.ecommerce.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}