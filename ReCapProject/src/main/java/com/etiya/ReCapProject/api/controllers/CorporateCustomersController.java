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

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.requests.create.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCorporateCustomerRequest;


@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		
	return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<CorporateCustomer>> getAll(){
		
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CorporateCustomer> getById(int id){
		return this.corporateCustomerService.getById(id);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
	

}
