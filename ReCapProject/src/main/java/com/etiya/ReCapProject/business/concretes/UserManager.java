package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.UserDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateUserRequest;
import com.etiya.ReCapProject.entities.requests.DeleteUserRequest;
import com.etiya.ReCapProject.entities.requests.UpdateUserRequest;
@Service
public class UserManager implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<ApplicationUser>> getAll() {
		return new SuccessDataResult<List<ApplicationUser>>(this.userDao.findAll());
	}

	@Override
	public DataResult<ApplicationUser> getById(int userId) {
		return new SuccessDataResult<ApplicationUser> (this.userDao.getById(userId));
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		
		ApplicationUser user = new ApplicationUser();
		user.setFirstName(createUserRequest.getFirstName());
		user.setLastName(createUserRequest.getLastName());
		user.setEmail(createUserRequest.getEmail());
		user.setPassword(createUserRequest.getPassword());
		
		this.userDao.save(user);
		return new SuccessResult(Messages.USER + " " + Messages.ADD);
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		
		ApplicationUser user = new ApplicationUser();
		user.setId(deleteUserRequest.getId());
		user.setFirstName(deleteUserRequest.getFirstName());
		
		this.userDao.delete(user);
		return new SuccessResult(Messages.USER + " " + Messages.DELETE);
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		
		ApplicationUser user = new ApplicationUser();
		user.setId(updateUserRequest.getId());
		user.setFirstName(updateUserRequest.getFirstName());
		user.setLastName(updateUserRequest.getLastName());
		user.setEmail(updateUserRequest.getEmail());
		user.setPassword(updateUserRequest.getPassword());
		
		this.userDao.save(user);
		return new SuccessResult(Messages.USER + " " + Messages.UPDATE);
	}

}
