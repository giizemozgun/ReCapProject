package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.core.entities.User;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

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
	public Result add(@RequestBody User user) {
		
	return this.userService.add(user);
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		
		return this.userService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<User> getById(int userId){
		return this.userService.getById(userId);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody User user) {
		return this.userService.update(user);
	}
	
	@PutMapping("/delete")
	public Result delete(@RequestBody User user) {
		return this.userService.delete(user);
	}
	
}
