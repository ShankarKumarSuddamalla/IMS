package com.tcs.userservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.userservice.entities.UserProfile;
import com.tcs.userservice.services.UserProfileService;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public List<UserProfile> customers() {
        return service.getCustomers();
    }

    @GetMapping("/suppliers")
    public List<UserProfile> suppliers() {
        return service.getSuppliers();
    }
}
