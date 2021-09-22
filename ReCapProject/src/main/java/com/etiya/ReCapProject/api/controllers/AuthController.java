package com.etiya.ReCapProject.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.UpdateUserProfileDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public Result delete(@Valid @RequestBody LoginRequest loginRequest) {
		return this.authService.login(loginRequest);
	}

	@PostMapping("/updateuserprofile")
	public Result register(@Valid @RequestBody UpdateUserProfileDto updateUserProfileDto) {
		return this.authService.updateUserProfile(updateUserProfileDto);
	}

}
