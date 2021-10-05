package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.car.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.car.UpdateCarRequest;

public interface CarService {
	
	DataResult<CarDetailDto> getById(int carId);
	Result add(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	
	DataResult<List<CarDetailDto>> getCarDetails();
	
	DataResult<List<CarDetailDto>> getByBrandId(int branId);
	
	DataResult<List<CarDetailDto>> getByColorId(int colorId);
	
	DataResult<List<CarDetailDto>> getAvailableCars();
	
	DataResult<List<CarDetailDto>> getByCity(String city);
	
	DataResult<CarDetailDto> getCarDetailByCarId(int carId);
	
	

}
