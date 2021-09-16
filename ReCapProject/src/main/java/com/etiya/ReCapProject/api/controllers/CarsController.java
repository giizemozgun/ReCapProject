package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;

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
	public void add(@RequestBody Car car) {
		
	 this.carService.add(car);
	}
	
	@GetMapping("/getall")
	public List<Car> getAll(){
		
		return this.carService.getAll();
	}
	
	@GetMapping("/getById")
	public Car getById( int carId){
		return this.carService.getById(carId);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Car car) {
		this.carService.update(car);
	}
	
	@PutMapping("/delete")
	public void delete(@RequestBody Car car) {
		this.carService.delete(car);
	}
	
	@GetMapping("/getcarsDetails")
	public List<CarDetailDto> getCarDetails() {
		return this.carService.getCarDetails();
	}
}
