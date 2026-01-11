package com.tcs.userservice.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.userservice.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    List<UserProfile> findByRole(String role);

    boolean existsByEmail(String email);
}


