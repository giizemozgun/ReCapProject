package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.entities.concretes.Brand;

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
	public void add(@RequestBody Brand brand) {
		
	 this.brandService.add(brand);
	}
	
	@GetMapping("/getall")
	public List<Brand> getAll(){
		
		return this.brandService.getAll();
	}
	
	@GetMapping("/getById")
	public Brand getById(int brandId){
		return this.brandService.getById(brandId);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Brand brand) {
		this.brandService.update(brand);
	}
	
	@PutMapping("/delete")
	public void delete(@RequestBody Brand brand) {
		this.brandService.delete(brand);
	}
	
}
