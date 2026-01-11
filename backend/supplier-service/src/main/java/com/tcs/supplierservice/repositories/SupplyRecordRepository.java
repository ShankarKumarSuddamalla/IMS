package com.tcs.supplierservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.supplierservice.entities.SupplyRecord;

public interface SupplyRecordRepository extends JpaRepository<SupplyRecord, Long> {

    List<SupplyRecord> findBySupplierEmail(String email);
}

