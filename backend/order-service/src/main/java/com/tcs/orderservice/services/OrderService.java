package com.tcs.orderservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.orderservice.client.InventoryClient;
import com.tcs.orderservice.dtos.OrderItemRequest;
import com.tcs.orderservice.dtos.OrderRequest;
import com.tcs.orderservice.dtos.OrderResponse;
import com.tcs.orderservice.dtos.StockReduceRequest;
import com.tcs.orderservice.entities.Order;
import com.tcs.orderservice.entities.OrderItem;
import com.tcs.orderservice.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository,
                        InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request,
                                    String customerEmail,
                                    String authHeader) {

        Order order = new Order();
        order.setCustomerEmail(customerEmail);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItemRequest itemReq : request.getItems()) {

            // 🔻 Reduce stock FIRST (important)
            inventoryClient.reduceStock(
                    new StockReduceRequest(
                            itemReq.getProductId(),
                            itemReq.getQuantity()
                    ),
                    authHeader
            );

            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setProductName(itemReq.getProductName());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(itemReq.getPrice());

            double subtotal = itemReq.getPrice() * itemReq.getQuantity();
            item.setSubTotal(subtotal);
            item.setOrder(order);

            orderItems.add(item);
            totalAmount += subtotal;
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setStatus("COMPLETED");

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate()
        );
    }

    public List<Order> getOrdersByCustomer(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
