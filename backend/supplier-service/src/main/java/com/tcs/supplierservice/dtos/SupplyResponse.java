package com.tcs.supplierservice.dtos;
import java.time.LocalDateTime;

public class SupplyResponse {

    private Long supplyId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime supplyDate;
    private String status;

    public SupplyResponse(Long supplyId, Long productId,
                          Integer quantity, LocalDateTime supplyDate,
                          String status) {
        this.supplyId = supplyId;
        this.productId = productId;
        this.quantity = quantity;
        this.supplyDate = supplyDate;
        this.status = status;
    }

    public Long getSupplyId() { return supplyId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public LocalDateTime getSupplyDate() { return supplyDate; }
    public String getStatus() { return status; }
}

