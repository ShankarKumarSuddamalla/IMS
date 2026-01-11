package com.tcs.supplierservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.supplierservice.dtos.SupplyRequest;
import com.tcs.supplierservice.dtos.SupplyResponse;
import com.tcs.supplierservice.entities.SupplyRecord;
import com.tcs.supplierservice.services.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    // SUPPLIER – supply stock
    @PostMapping("/supply")
    public SupplyResponse supply(
            @RequestBody SupplyRequest request,
            @RequestHeader("X-USER-EMAIL") String email,
            @RequestHeader("Authorization") String authHeader) {

        return service.supplyStock(request, email, authHeader);
    }

    // SUPPLIER – view own supplies
    @GetMapping("/supplies")
    public List<SupplyRecord> mySupplies(
            @RequestHeader("X-USER-EMAIL") String email) {

        return service.mySupplies(email);
    }

    // ADMIN – view all supplies
    @GetMapping("/all-supplies")
    public List<SupplyRecord> allSupplies() {
        return service.allSupplies();
    }
}

