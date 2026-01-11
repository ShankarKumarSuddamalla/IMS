package com.tcs.orderservice.dtos;

import java.time.LocalDateTime;

public class OrderResponse {
	private Long orderId;
	private Double totalAmount;
	private String status;
	private LocalDateTime orderDate;
	public OrderResponse(Long orderId,Double totalAmount,String status,LocalDateTime orderDate) {
		this.orderId=orderId;
		this.totalAmount=totalAmount;
		this.status=status;
		this.orderDate=orderDate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	
}
