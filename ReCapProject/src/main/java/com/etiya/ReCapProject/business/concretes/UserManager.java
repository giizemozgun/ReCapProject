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
import com.etiya.ReCapProject.entities.requests.create.CreateUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateUserRequest;
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
		
		user.setEmail(createUserRequest.getEmail());
		user.setPassword(createUserRequest.getPassword());
		
		this.userDao.save(user);
		return new SuccessResult(Messages.UserAdded);
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		
		ApplicationUser user = new ApplicationUser();
		user.setId(deleteUserRequest.getId());
		
		this.userDao.delete(user);
		return new SuccessResult(Messages.UserDeleted);
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		
		ApplicationUser user = new ApplicationUser();
		user.setId(updateUserRequest.getId());
		
		user.setEmail(updateUserRequest.getEmail());
		user.setPassword(updateUserRequest.getPassword());
		
		this.userDao.save(user);
		return new SuccessResult(Messages.UserUpdated);
	}

}
