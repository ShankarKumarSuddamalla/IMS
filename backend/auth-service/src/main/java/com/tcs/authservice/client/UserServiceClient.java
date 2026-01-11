package com.tcs.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tcs.authservice.dtos.UserProfileRequest;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/internal/users/create")
    void createUserProfile(@RequestBody UserProfileRequest request);
}

