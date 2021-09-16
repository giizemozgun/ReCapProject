package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;

public interface CarService {
	
	List<Car> getAll();
	Car getById(int carId);
	void add(Car car);
	void delete(Car car);
	void update(Car car);
	
	List<CarDetailDto> getCarDetails();

}
