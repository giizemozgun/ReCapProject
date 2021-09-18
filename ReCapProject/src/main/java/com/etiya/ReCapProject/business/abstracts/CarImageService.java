package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

public interface CarImageService {
	
	DataResult<List<CarImage>> getAll();
	DataResult<CarImage> getById(int id);
	Result add(CreateCarImageRequest createCarImageRequest);
	Result delete(DeleteCarImageRequest deleteCarImageRequest);
	Result update(UpdateCarImageRequest updateCarImageRequest);
	
	DataResult<List<CarImage>> getByCarId(int carId);
}
