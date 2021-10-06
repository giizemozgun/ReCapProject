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
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.car.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.car.UpdateCarRequest;

@Service
public class CarManager implements CarService {
	private CarDao carDao;
	private ModelMapper modelMapper;

	@Autowired
	public CarManager(CarDao carDao, ModelMapper modelMapper) {
		super();
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CarDetailDto>> getAvailableCars() {
		List<Car> cars = this.carDao.getByIsAvailableIsTrue();

		return new SuccessDataResult<List<CarDetailDto>>(this.convertCarDetailDtos(cars),Messages.AvailableCarListed);
	}

	@Override
	public DataResult<CarDetailDto> getById(int carId) {
		Car car = this.carDao.getById(carId);
		CarDetailDto carDetailDtos = modelMapper.map(car, CarDetailDto.class);

		return new SuccessDataResult<CarDetailDto>(carDetailDtos, Messages.GetCar);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Car car = modelMapper.map(createCarRequest, Car.class);
		//car.setAvailable(true);

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

		Car car = modelMapper.map(updateCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.CarUpdated);

	}

	@Override
	public DataResult<List<CarDetailDto>> getCarDetails() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarWithBrandAndColorDetails(),Messages.CarsListed);
	}

	@Override
	public DataResult<List<CarDetailDto>> getByBrandId(int brandId) {
		List<Car> cars = this.carDao.getByBrand_BrandId(brandId);

		return new SuccessDataResult<List<CarDetailDto>>(this.convertCarDetailDtos(cars), Messages.CarsListedByBrand);
	}

	@Override
	public DataResult<List<CarDetailDto>> getByColorId(int colorId) {

		List<Car> cars = this.carDao.getByColor_ColorId(colorId);

		return new SuccessDataResult<List<CarDetailDto>>(this.convertCarDetailDtos(cars),Messages.CarsListedByColor);
	}

	@Override
	public DataResult<List<CarDetailDto>> getByCity(String city) {
		List<Car> cars = this.carDao.getByCity(city);
		
		return new SuccessDataResult<List<CarDetailDto>>(this.convertCarDetailDtos(cars),Messages.CarsListedByCity);
	}

	private List<CarDetailDto> convertCarDetailDtos(List<Car> cars) {
		List<CarDetailDto> carDetailDtos =cars.stream().map(car -> modelMapper.map(car, CarDetailDto.class)).collect(Collectors.toList());
		return carDetailDtos;
	}

}
