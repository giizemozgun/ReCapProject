package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.create.CreateUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateUserRequest;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateUserRequest createUserRequest) {
		
	return this.userService.add(createUserRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ApplicationUser>> getAll(){
		
		return this.userService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<ApplicationUser> getById(int userId){
		return this.userService.getById(userId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}
	
}
