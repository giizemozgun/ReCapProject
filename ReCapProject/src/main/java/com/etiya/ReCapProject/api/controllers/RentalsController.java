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

import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalRequest;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@PostMapping("/addforindividualcustomer")
	public Result addForIndividualCustomer(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		
	return this.rentalService.addForIndividualCustomer(createRentalRequest);
	}
	
	@PostMapping("/addforcorporatecustomer")
	public Result addForCorporateustomer(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		
	return this.rentalService.addForCorporateCustomer(createRentalRequest);
	}
	
	
	
	@GetMapping("/getall")
	public DataResult<List<Rental>> getAll(){
		
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Rental> getById(int rentalId){
		return this.rentalService.getById(rentalId);
	}
	
	@PostMapping("/updateforcorporatecustomer")
	public Result updateForCorporateCustomer(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.updateForCorporateCustomer(updateRentalRequest);
	}
	
	@PostMapping("/updateforindividualcustomer")
	public Result updateForIndividualCustomer(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.updateForIndividualCustomer(updateRentalRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}
	
	@PostMapping("validateCarReturned")
	public Result validateCarReturned(@Valid @RequestBody CarReturnedRequest carReturnedRequest) {
		return this.rentalService.validateCarReturned(carReturnedRequest);
	}
}
