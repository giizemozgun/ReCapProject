package com.etiya.ReCapProject.business.concretes;


import java.util.List;


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


	@Autowired
	public CarManager(CarDao carDao ) {
		super();
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>(this.carDao.findAll());
	}
	
	@Override
	public DataResult<List<Car>> getAvailableCars() {
		List<Car> cars = this.carDao.findAll();          
		List<Car> maintenanceCars = this.carDao.getByMaintenances_ReturnDateIsNullAndMaintenances_MaintenanceDateIsNotNull();          
		cars.removeAll(maintenanceCars);     
		
		return new SuccessDataResult<List<Car>>(cars);	
	}

	@Override
	public DataResult<Car> getById(int carId) {
		
		return new SuccessDataResult<Car>(this.carDao.getById(carId));
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
	public DataResult<List<Car>> getByBrandId(int branId) {
		
		return new SuccessDataResult<List<Car>>(this.carDao.getByBrand_BrandId(branId));
	}

	@Override
	public DataResult<List<Car>> getByColorId(int colorId) {
		
		return new SuccessDataResult<List<Car>>(this.carDao.getByColor_ColorId(colorId));
	}

	@Override
	public DataResult<List<Car>> getByCity(String city) {
		return new SuccessDataResult<List<Car>>(this.carDao.getByCity(city));
	}
	

	
	
	
}
