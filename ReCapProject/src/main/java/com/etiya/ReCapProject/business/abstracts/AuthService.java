package com.etiya.ReCapProject.business.abstracts;


import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.requests.user.LoginRequest;

public interface AuthService {
		
	
	Result login(LoginRequest loginRequest);

	
}
