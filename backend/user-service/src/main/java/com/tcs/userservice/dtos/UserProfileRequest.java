package com.tcs.userservice.dtos;

import lombok.Data;

@Data
public class UserProfileRequest {
    private Long authUserId;
    private String name;
    private String email;
    private String role;
    private String phone;
    private String address;
	public Long getAuthUserId() {
		return authUserId;
	}
	public void setAuthUserId(Long authUserId) {
		this.authUserId = authUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
