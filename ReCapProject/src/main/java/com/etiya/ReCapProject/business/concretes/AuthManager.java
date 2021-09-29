package com.etiya.ReCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.UserDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.dtos.UpdateUserProfileDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;

@Service
public class AuthManager implements AuthService {

	private UserDao userDao;

	@Autowired
	public AuthManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		
		var result = BusinessRules.run(checkToEmail(loginRequest.getEmail()),
				checkToPassword(loginRequest.getEmail(), loginRequest.getPassword()));

		if (result != null) {
			return result;
		}
		return new SuccessResult(Messages.Login);
	}

	private Result checkToEmail(String email) {

		if (this.userDao.getByEmail(email) == null) {
			return new ErrorResult(Messages.UserNotFound);
		}
		return new SuccessResult(Messages.Success);

	}
	
	private Result checkToPassword(String email, String password) {

		if (this.userDao.getByEmail(email)  != this.userDao.getByPassword(password))  {
			return new ErrorResult(Messages.WrongPassword);
		}
		return new SuccessResult(Messages.Success);

	}

	
	public Result updateUserProfile(UpdateUserProfileDto updateUserProfileDto) {
	
		
		ApplicationUser applicationUser = new ApplicationUser();
		
		applicationUser.setId(updateUserProfileDto.getId());
		applicationUser.setEmail(updateUserProfileDto.getEmail());
		applicationUser.setPassword(updateUserProfileDto.getPassword());

		this.userDao.save(applicationUser);
		return new SuccessResult(Messages.UserUpdated);
	}

}
