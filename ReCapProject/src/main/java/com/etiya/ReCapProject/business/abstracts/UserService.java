package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.ApplicationUserDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateUserRequest;

public interface UserService {
	

	DataResult<List<ApplicationUserDetailDto>> getAll();
	DataResult<ApplicationUserDetailDto> getById(int userId);
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
}
