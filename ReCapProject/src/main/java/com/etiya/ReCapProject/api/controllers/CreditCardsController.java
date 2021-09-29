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

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

import com.etiya.ReCapProject.entities.concretes.CreditCard;

import com.etiya.ReCapProject.entities.requests.CreateCreditCardRequest;

import com.etiya.ReCapProject.entities.requests.DeleteCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCreditCardRequest;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardsController {
	

	private CreditCardService creditCardService;
	
	@Autowired
	public CreditCardsController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCreditCardRequest createCreditCardRequest) {
		
	return this.creditCardService.add(createCreditCardRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<CreditCard>> getAll(){
		
		return this.creditCardService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CreditCard> getById(int creditCardId){
		return this.creditCardService.getById(creditCardId);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		
	return this.creditCardService.update(updateCreditCardRequest);
	}
}
