package com.tcs.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.userservice.dtos.UserProfileRequest;
import com.tcs.userservice.entities.UserProfile;
import com.tcs.userservice.repositories.UserProfileRepository;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;
    

    public UserProfileService(UserProfileRepository repository) {
		super();
		this.repository = repository;
	}

	public UserProfile createProfile(UserProfileRequest request) {

        UserProfile profile = new UserProfile();
        profile.setAuthUserId(request.getAuthUserId());
        profile.setName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setRole(request.getRole());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());

        return repository.save(profile);
    }

    public UserProfile getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfile getByAuthUserId(Long authUserId) {
        return repository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<UserProfile> viewAllUsers(){
    	return repository.findAll();
    }
}
