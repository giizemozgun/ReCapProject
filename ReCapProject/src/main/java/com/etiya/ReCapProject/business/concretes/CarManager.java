package com.etiya.ReCapProject.business.concretes;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.car.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.car.UpdateCarRequest;

@Service
public class CarManager implements CarService {
	private CarDao carDao;
	private ModelMapper modelMapper;


	@Autowired
	public CarManager(CarDao carDao,ModelMapper modelMapper ) {
		super();
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public DataResult<List<CarDetailDto>> getAvailableCars() {
		List<Car> cars= this.carDao.getByIsAvailableIsTrue();
		 
        return new SuccessDataResult<List<CarDetailDto>>(this.getCarDetailDtos(cars));
	}

	@Override
	public DataResult<CarDetailDto> getById(int carId) {
		Car car = this.carDao.getById(carId);
		CarDetailDto carDetailDtos = modelMapper.map(car,CarDetailDto.class);
		carDetailDtos.setBrandName(car.getBrand().getBrandName());
		carDetailDtos.setColorName(car.getColor().getColorName());
		
		return new SuccessDataResult<CarDetailDto>(carDetailDtos);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		
		Brand brand = modelMapper.map(createCarRequest, Brand.class);
		Color color = modelMapper.map(createCarRequest, Color.class);
		
		Car car = modelMapper.map(createCarRequest, Car.class);
		car.setAvailable(true);
		car.setBrand(brand);
		car.setColor(color);
			
	 this.carDao.save(car);
	 return new SuccessResult(Messages.CarAdded);
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		Car car = modelMapper.map(deleteCarRequest, Car.class);
		
		this.carDao.delete(car);
		return new SuccessResult(Messages.CarDeleted);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Brand brand = modelMapper.map(updateCarRequest, Brand.class);
		Color color = modelMapper.map(updateCarRequest, Color.class);
		
		Car car = modelMapper.map(updateCarRequest, Car.class);
		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CarUpdated);
		
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarWithBrandAndColorDetails());
	}

	@Override
	public DataResult<List<CarDetailDto>> getByBrandId(int brandId) {
		
		List<Car> cars= this.carDao.getByBrand_BrandId(brandId);
		 
        return new SuccessDataResult<List<CarDetailDto>>(this.getCarDetailDtos(cars));
	}

	@Override
	public DataResult<List<CarDetailDto>> getByColorId(int colorId) {
		
		List<Car> cars= this.carDao.getByColor_ColorId(colorId);
		
        return new SuccessDataResult<List<CarDetailDto>>(this.getCarDetailDtos(cars));
	}

	@Override
	public DataResult<List<CarDetailDto>> getByCity(String city) {
		List<Car> cars= this.carDao.getByCity(city);
		
        return new SuccessDataResult<List<CarDetailDto>>(this.getCarDetailDtos(cars));
	}

	private List<CarDetailDto> getCarDetailDtos(List<Car> cars){
		List<CarDetailDto> carDetailDtos = new ArrayList<CarDetailDto>();

        for (Car car : cars) {
        	CarDetailDto carDetailDto = modelMapper.map(car, CarDetailDto.class);
            carDetailDto.setBrandName(this.carDao.getById(car.getCarId()).getBrand().getBrandName());
            carDetailDto.setColorName(this.carDao.getById(car.getCarId()).getColor().getColorName());

            carDetailDtos.add(carDetailDto);
        }
        return carDetailDtos;
	}
	
}
