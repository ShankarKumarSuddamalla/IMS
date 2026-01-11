package com.tcs.inventory_service.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tcs.inventory_service.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductId(Long productId);

    List<Inventory> findByAvailableQuantityLessThanEqual(Integer quantity);
}

