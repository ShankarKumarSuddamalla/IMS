package com.tcs.userservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.userservice.entities.UserProfile;
import com.tcs.userservice.repositories.UserProfileRepository;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    private final UserProfileRepository repository;

    public InternalUserController(UserProfileRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserProfile profile) {

        if (repository.existsByEmail(profile.getEmail())) {
            return; // idempotent
        }

        repository.save(profile);
    }
}

