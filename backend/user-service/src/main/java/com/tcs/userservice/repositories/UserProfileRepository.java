package com.tcs.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.userservice.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findByAuthUserId(Long authUserId);
}

