package com.tcs.reportservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tcs.reportservice.dtos.InventoryDTO;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory")
    List<InventoryDTO> getInventory();
}

