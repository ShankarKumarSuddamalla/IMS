package com.tcs.product_service.dtos;

public class InventoryInitRequest {

    private Long productId;
    private Integer quantity;

    public InventoryInitRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
