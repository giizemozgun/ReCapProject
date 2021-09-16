package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;

@Service
public class CarManager implements CarService {
	private CarDao carDao;

	@Autowired
	public CarManager(CarDao carDao) {
		super();
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>(this.carDao.findAll());
	}

	@Override
	public DataResult<Car> getById(int carId) {
		
		return new SuccessDataResult<Car>(this.carDao.getById(carId));
	}

	@Override
	public Result add(Car car) {
	 this.carDao.save(car);
	 return new SuccessResult("Araba Eklendi");
		
	}

	@Override
	public Result delete(Car car) {
		this.carDao.delete(car);
		 return new SuccessResult("Araba Silindi");
	}

	@Override
	public Result update(Car car) {
		 this.carDao.save(car);
		 return new SuccessResult("Araba Güncellendi");
		
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarWithBrandAndColorDetails());
	}
	
}
