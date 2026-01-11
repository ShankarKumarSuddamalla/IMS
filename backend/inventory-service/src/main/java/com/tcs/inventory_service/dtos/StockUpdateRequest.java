package com.tcs.inventory_service.dtos;

public class StockUpdateRequest {

    private Long productId;
    private Integer quantity;

    public StockUpdateRequest() {}

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
