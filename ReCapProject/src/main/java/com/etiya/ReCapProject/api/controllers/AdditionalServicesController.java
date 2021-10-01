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

import com.etiya.ReCapProject.business.abstracts.AdditionalServiceService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import com.etiya.ReCapProject.entities.requests.create.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateAdditionalServiceRequest;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {
	
	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
	return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List< AdditionalService>> getAll(){
		
		return this.additionalServiceService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<AdditionalService> getById(int brandId){
		return this.additionalServiceService.getById(brandId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
	}
}
