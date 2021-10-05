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

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.car.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.car.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	
	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarRequest createCarRequest) {
		
	 return this.carService.add(createCarRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<CarDetailDto> getById( int carId){
		return this.carService.getById(carId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	
	@GetMapping("/getcarsDetails")
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return this.carService.getCarDetails();
	}
	
	@GetMapping("/getcardetailbycarid")
	public DataResult<CarDetailDto> getCarDetailByCarId(int carId) {
		return this.carService.getCarDetailByCarId(carId);
	}
	
	
			
	@GetMapping("/getbybrandid")
	public DataResult<List<CarDetailDto>> getByBrandId( int brandId){
		return this.carService.getByBrandId(brandId);
	}
	
	@GetMapping("/getbycolorid")
	public DataResult<List<CarDetailDto>> getByColorId( int colorId){
		return this.carService.getByColorId(colorId);
	}
	@GetMapping("/getavailablecars")
	public DataResult<List<CarDetailDto>> getAvailableCars(){
		return this.carService.getAvailableCars();
	}
	@GetMapping("/getcity")
	public DataResult<List<CarDetailDto>> getByCity(String city){
		return this.carService.getByCity(city);
	}
	
	
}
