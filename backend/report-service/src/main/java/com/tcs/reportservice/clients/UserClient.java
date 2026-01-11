package com.tcs.reportservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tcs.reportservice.dtos.UserDTO;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/customers")
    List<UserDTO> getCustomers();

    @GetMapping("/users/suppliers")
    List<UserDTO> getSuppliers();
}

