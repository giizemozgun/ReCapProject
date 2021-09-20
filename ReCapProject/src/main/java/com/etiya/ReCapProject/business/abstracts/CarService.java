package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarRequest;

public interface CarService {
	
	DataResult<List<Car>> getAll();
	DataResult<Car> getById(int carId);
	Result add(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	
	DataResult<List<CarDetailDto>> getCarDetails();
	
	DataResult<List<Car>> getByBrandId(int branId);
	
	DataResult<List<Car>> getByColorId(int colorId);
	

}
