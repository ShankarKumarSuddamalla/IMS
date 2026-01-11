package com.tcs.inventory_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.inventory_service.dtos.InventoryInitRequest;
import com.tcs.inventory_service.dtos.InventoryResponse;
import com.tcs.inventory_service.entities.Inventory;
import com.tcs.inventory_service.repositories.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void createInventory(InventoryInitRequest request) {

        Inventory inventory = new Inventory(
                request.getProductId(),
                request.getQuantity(),
                5
        );

        repository.save(inventory);
    }

    public List<InventoryResponse> getAllInventory() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<InventoryResponse> getLowStock() {
        return repository.findAll()
                .stream()
                .filter(i -> i.getAvailableQuantity() <= i.getReorderLevel())
                .map(this::map)
                .collect(Collectors.toList());
    }
 // ADMIN: overwrite stock quantity
    public void updateStock(Long productId, Integer quantity) {

        Inventory inventory = repository.findByProductId(productId);

        if (inventory == null) {
            throw new RuntimeException("Inventory not found for product " + productId);
        }

        inventory.setAvailableQuantity(quantity);
        repository.save(inventory);
    }

    // SUPPLIER: add stock quantity
    public void addStock(Long productId, Integer quantity) {

        Inventory inventory = repository.findByProductId(productId);

        if (inventory == null) {
            throw new RuntimeException("Inventory not found for product " + productId);
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );
        repository.save(inventory);
    }
    public void reduceStock(Long productId,Integer quantity) {
    	Inventory inventory=repository.findByProductId(productId);
    	if(inventory==null) {
    		throw new RuntimeException("Inventory not found for product "+productId);
    	}
    	if(inventory.getAvailableQuantity()<quantity){
    		throw new RuntimeException("Insufficient stock for product "+productId);
    	}
    	inventory.setAvailableQuantity(inventory.getAvailableQuantity()-quantity);
    	repository.save(inventory);
    }


    private InventoryResponse map(Inventory i) {
        return new InventoryResponse(
                i.getProductId(),
                i.getAvailableQuantity(),
                i.getReorderLevel(),
                i.getAvailableQuantity() <= i.getReorderLevel()
        );
    }
}

