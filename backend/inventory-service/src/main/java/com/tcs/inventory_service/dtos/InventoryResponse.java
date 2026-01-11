package com.tcs.inventory_service.dtos;


public class InventoryResponse {

    private Long productId;
    private Integer availableQuantity;
    private Integer reorderLevel;
    private boolean lowStock;

    public InventoryResponse(Long productId, Integer availableQuantity,
                             Integer reorderLevel, boolean lowStock) {
        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.reorderLevel = reorderLevel;
        this.lowStock = lowStock;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public boolean isLowStock() {
        return lowStock;
    }
}
