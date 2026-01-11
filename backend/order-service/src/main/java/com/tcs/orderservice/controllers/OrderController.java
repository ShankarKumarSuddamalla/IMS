package com.tcs.orderservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.orderservice.dtos.OrderRequest;
import com.tcs.orderservice.dtos.OrderResponse;
import com.tcs.orderservice.entities.Order;
import com.tcs.orderservice.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // CUSTOMER – place order
    @PostMapping
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request,
            @RequestHeader("X-USER-EMAIL") String email,
            @RequestHeader("Authorization") String authHeader) {

        return service.placeOrder(request, email, authHeader);
    }

    // CUSTOMER – view own orders
    @GetMapping("/my")
    public List<Order> myOrders(
            @RequestHeader("X-USER-EMAIL") String email) {

        return service.getOrdersByCustomer(email);
    }

    // ADMIN – view all orders
    @GetMapping
    public List<Order> allOrders() {
        return service.getAllOrders();
    }
}

