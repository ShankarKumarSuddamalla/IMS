package com.tcs.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")

public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authUserId;     

    private String name;
    private String email;

    @Column(length = 20)
    private String role;         

    private String phone;
    private String address;
	public UserProfile() {
		super();
	}
	
	public UserProfile(Long id, Long authUserId, String name, String email, String role, String phone, String address) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.name = name;
		this.email = email;
		this.role = role;
		this.phone = phone;
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

