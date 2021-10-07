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
import com.etiya.ReCapProject.entities.requests.user.LoginRequest;

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

		var result = BusinessRules.run(checkToEmailAndPassword(loginRequest));

		if (result != null) {
			return result;
		}
		return new SuccessResult(Messages.Login);
	}

	private Result checkToEmailAndPassword(LoginRequest loginRequest) {

		if (this.userDao.getByEmail(loginRequest.getEmail()) == null) {
			return new ErrorResult(Messages.IncorrectEntry);
		}
		if (!this.userDao.getByEmail(loginRequest.getEmail()).getPassword().equals(loginRequest.getPassword())) {
			return new ErrorResult(Messages.IncorrectEntry);
		}
		return new SuccessResult(Messages.Success);
	}

}
