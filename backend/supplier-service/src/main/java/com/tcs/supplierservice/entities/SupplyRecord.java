package com.tcs.supplierservice.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "supply_records")
public class SupplyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplierEmail;
    private Long productId;
    private Integer quantity;
    private LocalDateTime supplyDate;
    private String status; // SUPPLIED

    public SupplyRecord() {}

    public Long getId() { return id; }
    public String getSupplierEmail() { return supplierEmail; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public LocalDateTime getSupplyDate() { return supplyDate; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setSupplierEmail(String supplierEmail) { this.supplierEmail = supplierEmail; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setSupplyDate(LocalDateTime supplyDate) { this.supplyDate = supplyDate; }
    public void setStatus(String status) { this.status = status; }
}

