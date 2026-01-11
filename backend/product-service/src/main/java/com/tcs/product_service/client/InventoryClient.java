package com.tcs.product_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tcs.product_service.dtos.InventoryInitRequest;

@FeignClient(name="inventory-service")
public interface InventoryClient {
	@PostMapping("/inventory/init")
	void initInventory(
			@RequestBody InventoryInitRequest request,
			@RequestHeader("Authorization") String authHeader
			);
}
