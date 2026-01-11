package com.tcs.supplierservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.supplierservice.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findByEmail(String email);
}

