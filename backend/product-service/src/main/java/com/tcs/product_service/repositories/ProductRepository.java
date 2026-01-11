package com.tcs.product_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.product_service.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);
}
