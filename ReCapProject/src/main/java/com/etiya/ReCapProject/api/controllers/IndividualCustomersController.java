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

import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.individualCustomer.UpdateIndividualCustomerRequest;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
	return this.individualCustomerService.add(createIndividualCustomerRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<IndividualCustomerDetailDto>> getAll(){
		
		return this.individualCustomerService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<IndividualCustomerDetailDto> getById(int id){
		return this.individualCustomerService.getById(id);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	
}
