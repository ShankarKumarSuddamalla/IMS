package com.tcs.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tcs.orderservice.dtos.StockReduceRequest;

@FeignClient(name="inventory-service")
public interface InventoryClient {
	@PostMapping("/inventory/reduce")
	void reduceStock(
			@RequestBody StockReduceRequest request,
			@RequestHeader("Authorization") String authHeader
			);
}
