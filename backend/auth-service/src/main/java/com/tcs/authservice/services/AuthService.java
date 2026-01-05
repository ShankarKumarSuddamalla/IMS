package com.tcs.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.authservice.entities.Role;
import com.tcs.authservice.entities.User;
import com.tcs.authservice.repositories.RoleRepository;
import com.tcs.authservice.repositories.UserRepository;
import com.tcs.authservice.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().getName()
        );
    }

    public void register(String name, String email, String password, String roleName) {

        // 🔒 RULE: ONLY ONE ADMIN ALLOWED
        if ("ADMIN".equalsIgnoreCase(roleName)) {

            boolean adminExists = userRepository.existsByRole_Name("ADMIN");

            if (adminExists) {
                throw new RuntimeException("Admin already exists. Only one admin is allowed.");
            }
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
    }

}

