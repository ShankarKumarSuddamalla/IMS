package com.tcs.userservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.userservice.dtos.UserProfileRequest;
import com.tcs.userservice.dtos.UserProfileResponse;
import com.tcs.userservice.entities.UserProfile;
import com.tcs.userservice.services.UserProfileService;


@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService service;
    

    public UserProfileController(UserProfileService service) {
		super();
		this.service = service;
	}

	@PostMapping
    public UserProfileResponse create(@RequestBody UserProfileRequest request) {

        UserProfile profile = service.createProfile(request);

        return new UserProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getRole(),
                profile.getPhone(),
                profile.getAddress()
        );
    }
	
	  @GetMapping
	    public List<UserProfileResponse> getAllUsers() {

	        return service.viewAllUsers()
	                .stream()
	                .map(user -> new UserProfileResponse(
	                        user.getId(),
	                        user.getName(),
	                        user.getEmail(),
	                        user.getRole(),
	                        user.getPhone(),
	                        user.getAddress()
	                ))
	                .collect(Collectors.toList());
	    }
    @GetMapping("/email/{email}")
    public UserProfileResponse getByEmail(@PathVariable String email) {

        UserProfile profile = service.getByEmail(email);

        return new UserProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getRole(),
                profile.getPhone(),
                profile.getAddress()
        );
    }
}

