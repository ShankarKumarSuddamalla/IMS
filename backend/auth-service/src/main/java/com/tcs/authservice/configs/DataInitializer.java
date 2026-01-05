package com.tcs.authservice.configs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tcs.authservice.entities.Role;
import com.tcs.authservice.repositories.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.count() == 0) {

                roleRepository.save(new Role(null, "ADMIN"));
                roleRepository.save(new Role(null, "CUSTOMER"));
                roleRepository.save(new Role(null, "SUPPLIER"));
                roleRepository.save(new Role(null, "STAFF"));
            }
        };
    }
}

