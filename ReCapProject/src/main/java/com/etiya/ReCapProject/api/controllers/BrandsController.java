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

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.requests.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateBrandRequest createBrandrequest) {
		
	return this.brandService.add(createBrandrequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Brand>> getAll(){
		
		return this.brandService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Brand> getById(int brandId){
		return this.brandService.getById(brandId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	
}
