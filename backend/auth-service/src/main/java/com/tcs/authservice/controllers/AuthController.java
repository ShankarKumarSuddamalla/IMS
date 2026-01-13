package com.tcs.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.authservice.dtos.JwtResponse;
import com.tcs.authservice.dtos.LoginRequest;
import com.tcs.authservice.dtos.RegisterRequest;
import com.tcs.authservice.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return new JwtResponse(token);
    }
    
    @PostMapping("/register")   //THIS MUST EXIST
    public String register(@RequestBody RegisterRequest request) {
        authService.register(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            request.getRole()
        );
        return "User registered successfully";
    }
}