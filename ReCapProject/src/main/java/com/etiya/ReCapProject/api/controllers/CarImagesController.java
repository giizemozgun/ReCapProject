package com.etiya.ReCapProject.api.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

@RestController
@RequestMapping("/api/carimages")
public class CarImagesController {
	
	private CarImageService carImageService;

	@Autowired
	public CarImagesController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	@PostMapping("/add")
	public Result add(@RequestParam("carId") int carId, MultipartFile file) throws IOException {
		
		CreateCarImageRequest creataCarImageRequest = new CreateCarImageRequest();
		creataCarImageRequest.setCarId(carId);
		creataCarImageRequest.setFile(file);
		return this.carImageService.add(creataCarImageRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<CarImage>> getAll(){
		
		return this.carImageService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CarImage> getById(int id){
		return this.carImageService.getById(id);
	}
	
	@PostMapping("/update")
	public Result update(@Valid UpdateCarImageRequest carImage, MultipartFile file) throws IOException {
		carImage.setFile(file);
		return this.carImageService.update(carImage);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteCarImageRequest deleteCarImageRequest) {
		return this.carImageService.delete(deleteCarImageRequest);
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<CarImage>> getByCarId(int carId){
		return this.carImageService.getByCarId(carId);
	}
	

}
