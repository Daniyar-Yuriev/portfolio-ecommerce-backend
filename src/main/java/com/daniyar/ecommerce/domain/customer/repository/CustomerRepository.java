package com.daniyar.ecommerce.domain.customer.repository;


import com.daniyar.ecommerce.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}