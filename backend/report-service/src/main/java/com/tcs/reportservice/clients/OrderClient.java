package com.tcs.reportservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tcs.reportservice.dtos.OrderDTO;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/orders")
    List<OrderDTO> getOrders();
}

