package com.tcs.supplierservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tcs.supplierservice.dtos.SupplyRequest;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/inventory/add")
    void addStock(
        @RequestBody SupplyRequest request,
        @RequestHeader("Authorization") String authHeader
    );
}

