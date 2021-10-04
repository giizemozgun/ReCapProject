package com.etiya.ReCapProject.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

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
import com.etiya.ReCapProject.entities.requests.create.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarRequest;

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
		 
		 List<CarDetailDto> carDetailDtos=cars.stream().map(car -> modelMapper.map(car, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos);
	}

	@Override
	public DataResult<CarDetailDto> getById(int carId) {
		Car car = this.carDao.getById(carId);
		CarDetailDto carDetailDtos = modelMapper.map(car,CarDetailDto.class);
		
		return new SuccessDataResult<CarDetailDto>(carDetailDtos);
	}
	
	@Override
	public DataResult<CarDetailDto> getCarDetailByCarId(int carId) {
		Car car=this.carDao.getById(carId);
		
		CarDetailDto carDetailDto=new CarDetailDto();
		carDetailDto.setCarName(car.getCarName());
		carDetailDto.setBrandName(car.getBrand().getBrandName());
		carDetailDto.setColorName(car.getColor().getColorName());
		carDetailDto.setDailyPrice(car.getDailyPrice());
		
		return new SuccessDataResult<CarDetailDto>(carDetailDto);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Brand brand = new Brand();
		brand.setBrandId(createCarRequest.getBrandId());
		Color color = new Color();
		color.setColorId(createCarRequest.getColorId());	
		
		Car car = new Car();
		car.setCarName(createCarRequest.getCarName());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setDescription(createCarRequest.getDescription());
		car.setModelYear(createCarRequest.getModelYear());
		car.setMinFindexScore(createCarRequest.getMinFindexScore());
		car.setCity(createCarRequest.getCity());
		car.setKm(createCarRequest.getKm());
		car.setAvailable(true);
		
		car.setBrand(brand);
		car.setColor(color);
			
	 this.carDao.save(car);
	 return new SuccessResult(Messages.CarAdded);
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		Car car = new Car();
		car.setCarId(deleteCarRequest.getCarId());
		
		this.carDao.delete(car);
		 return new SuccessResult(Messages.CarDeleted);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(updateCarRequest.getBrandId());
		Color color = new Color();
		color.setColorId(updateCarRequest.getColorId());	
			
		Car car = new Car();
		car.setCarId(updateCarRequest.getCarId());
		car.setCarName(updateCarRequest.getCarName());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setDescription(updateCarRequest.getDescription());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setMinFindexScore(updateCarRequest.getMinFindexScore());
		car.setCity(updateCarRequest.getCity());
		car.setKm(updateCarRequest.getKm());
		
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
	public DataResult<List<CarDetailDto>> getByBrandId(int branId) {
		
		List<Car> cars= this.carDao.getByBrand_BrandId(branId);
		 
		 List<CarDetailDto> carDetailDtos=cars.stream().map(car -> modelMapper.map(car, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos);
	}

	@Override
	public DataResult<List<CarDetailDto>> getByColorId(int colorId) {
		
		List<Car> cars= this.carDao.getByColor_ColorId(colorId);
		 
		 List<CarDetailDto> carDetailDtos=cars.stream().map(car -> modelMapper.map(car, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos);
	}

	@Override
	public DataResult<List<CarDetailDto>> getByCity(String city) {
		List<Car> cars= this.carDao.getByCity(city);
		 
		 List<CarDetailDto> carDetailDtos=cars.stream().map(car -> modelMapper.map(car, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos);
	}

	
}
