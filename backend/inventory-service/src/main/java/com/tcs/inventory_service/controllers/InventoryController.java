package com.tcs.inventory_service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory_service.dtos.InventoryInitRequest;
import com.tcs.inventory_service.dtos.InventoryResponse;
import com.tcs.inventory_service.dtos.StockUpdateRequest;
import com.tcs.inventory_service.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // INTERNAL / ADMIN
    @PostMapping("/init")
    public void initInventory(@RequestBody InventoryInitRequest request) {
        service.createInventory(request);
    }

    // ADMIN
    @GetMapping
    public List<InventoryResponse> getAll() {
        return service.getAllInventory();
    }

    // ADMIN / SUPPLIER
    @GetMapping("/low-stock")
    public List<InventoryResponse> lowStock() {
        return service.getLowStock();
    }
 // ADMIN – set stock quantity
    @PutMapping("/update")
    public void updateStock(@RequestBody StockUpdateRequest request) {
        service.updateStock(request.getProductId(), request.getQuantity());
    }

    // SUPPLIER – add stock
    @PostMapping("/add")
    public void addStock(@RequestBody StockUpdateRequest request) {
        service.addStock(request.getProductId(), request.getQuantity());
    }
    @PostMapping("/reduce")
    public void reduceStock(@RequestBody StockUpdateRequest request) {
    	service.reduceStock(request.getProductId(),request.getQuantity());
    }

}

