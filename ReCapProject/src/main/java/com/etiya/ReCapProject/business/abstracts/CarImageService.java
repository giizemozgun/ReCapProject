package com.etiya.ReCapProject.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.CarImageDetailDto;
import com.etiya.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

public interface CarImageService {
	
	DataResult<List<CarImageDetailDto>> getAll();
	DataResult<CarImageDetailDto> getById(int id);
	Result add(CreateCarImageRequest createCarImageRequest) throws IOException;
	Result delete(DeleteCarImageRequest deleteCarImageRequest);
	Result update(UpdateCarImageRequest updateCarImageRequest)throws IOException;
	
	DataResult<List<CarImageDetailDto>> getByCarId(int carId);
}
