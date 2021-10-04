package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.UserDao;
import com.etiya.ReCapProject.entities.abstracts.ApplicationUser;
import com.etiya.ReCapProject.entities.dtos.ApplicationUserDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateUserRequest;
@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public UserManager(UserDao userDao,ModelMapper modelMapper) {
		super();
		this.userDao = userDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<ApplicationUserDetailDto>> getAll() {
		List<ApplicationUser> applicationUsers= this.userDao.findAll();
		 
		 List<ApplicationUserDetailDto> applicationUserDetailDtos =applicationUsers.stream().map(applicationUser -> modelMapper.map(applicationUser, ApplicationUserDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ApplicationUserDetailDto>>(applicationUserDetailDtos);
	}

	@Override
	public DataResult<ApplicationUserDetailDto> getById(int userId) {
		ApplicationUser applicationUser = this.userDao.getById(userId);
		ApplicationUserDetailDto applicationUserDetailDto = modelMapper.map(applicationUser,ApplicationUserDetailDto.class);
		
		return new SuccessDataResult<ApplicationUserDetailDto>(applicationUserDetailDto);
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
