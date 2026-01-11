package com.tcs.orderservice.dtos;

public class StockReduceRequest {

	private Long productId;
	private Integer quantity;
	public StockReduceRequest(Long productId,Integer quantity) {
		this.productId=productId;
		this.quantity=quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
}
