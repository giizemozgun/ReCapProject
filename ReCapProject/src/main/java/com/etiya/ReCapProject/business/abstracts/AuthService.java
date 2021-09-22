package com.etiya.ReCapProject.business.abstracts;


import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.UpdateUserProfileDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;

public interface AuthService {
		
	
	Result login(LoginRequest loginRequest);

	Result updateUserProfile(UpdateUserProfileDto updateUserProfileDto );
	
}
