package com.tcs.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.orderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
	List<Order> findByCustomerEmail(String customerEmail);
}
