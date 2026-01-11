package com.tcs.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.orderservice.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
