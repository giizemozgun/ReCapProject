package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
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
	public List<Car> getAll() {
		return this.carDao.findAll();
	}

	@Override
	public Car getById(int carId) {
		
		return this.carDao.getById(carId);
	}

	@Override
	public void add(Car car) {
	 this.carDao.save(car);
		
	}

	@Override
	public void delete(Car car) {
		this.carDao.delete(car);
		
	}

	@Override
	public void update(Car car) {
		 this.carDao.save(car);
		
	}

	@Override
	public List<CarDetailDto> getCarDetails() {
		return this.carDao.getCarWithBrandAndColorDetails();
	}
	
}
