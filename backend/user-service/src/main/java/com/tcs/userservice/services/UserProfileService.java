package com.tcs.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.userservice.entities.UserProfile;
import com.tcs.userservice.repositories.UserProfileRepository;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public List<UserProfile> getCustomers() {
        return repository.findByRole("CUSTOMER");
    }

    public List<UserProfile> getSuppliers() {
        return repository.findByRole("SUPPLIER");
    }
}
