package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.entities.User;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface UserService {
	

	DataResult<List<User>> getAll();
	DataResult<User> getById(int userId);
	Result add(User user);
	Result delete(User user);
	Result update(User user);
}
