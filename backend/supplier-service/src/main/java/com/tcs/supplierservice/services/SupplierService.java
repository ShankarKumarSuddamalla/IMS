package com.tcs.supplierservice.services;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.supplierservice.client.InventoryClient;
import com.tcs.supplierservice.dtos.SupplyRequest;
import com.tcs.supplierservice.dtos.SupplyResponse;
import com.tcs.supplierservice.entities.SupplyRecord;
import com.tcs.supplierservice.repositories.SupplyRecordRepository;

@Service
public class SupplierService {

    private final SupplyRecordRepository supplyRepo;
    private final InventoryClient inventoryClient;

    public SupplierService(SupplyRecordRepository supplyRepo,
                           InventoryClient inventoryClient) {
        this.supplyRepo = supplyRepo;
        this.inventoryClient = inventoryClient;
    }

    // SUPPLIER – supply stock
    public SupplyResponse supplyStock(SupplyRequest request,
                                      String supplierEmail,
                                      String authHeader) {

        // 🔗 Add stock to inventory
        inventoryClient.addStock(request, authHeader);

        // Save supply record
        SupplyRecord record = new SupplyRecord();
        record.setSupplierEmail(supplierEmail);
        record.setProductId(request.getProductId());
        record.setQuantity(request.getQuantity());
        record.setSupplyDate(LocalDateTime.now());
        record.setStatus("SUPPLIED");

        SupplyRecord saved = supplyRepo.save(record);

        return new SupplyResponse(
                saved.getId(),
                saved.getProductId(),
                saved.getQuantity(),
                saved.getSupplyDate(),
                saved.getStatus()
        );
    }

    public List<SupplyRecord> mySupplies(String email) {
        return supplyRepo.findBySupplierEmail(email);
    }

    public List<SupplyRecord> allSupplies() {
        return supplyRepo.findAll();
    }
}

