package com.etiya.ReCapProject.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService{

	private CarImageDao carImageDao;
	
	@Autowired
	public CarImageManager(CarImageDao carImageDao) {
		super();
		this.carImageDao = carImageDao;
	}

	@Override
	public DataResult<List<CarImage>> getAll() {
		
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll());
	}

	@Override
	public DataResult<CarImage> getById(int id) {
	
		return new SuccessDataResult<CarImage>(this.carImageDao.getById(id));
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) {
		
		var result = BusinessRules.run(checkIfCarImageLimitExceeded(createCarImageRequest.getCarId(),5));

		if (result != null) {
			return result;
		}
			
		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());
		
		LocalDate date = LocalDate.now();
		
		String imagePath = UUID.randomUUID().toString();
		
		
		CarImage carImage = new CarImage();
		carImage.setCar(car);
		carImage.setDate(date);
		carImage.setImagePath("carImages/" + imagePath + ".jpg");
		//carImage.setImagePath(createCarImageRequest.getImagePath());		
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.ADD) ;
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		Car car = new Car();
		car.setCarId(deleteCarImageRequest.getCarId());
		
		
		CarImage carImage = new CarImage();
		
		carImage.setId(deleteCarImageRequest.getId());
		carImage.setCar(car);
		
		this.carImageDao.delete(carImage);
		return new SuccessResult(Messages.DELETE);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) {
		
		Car car = new Car();
		car.setCarId(updateCarImageRequest.getCarId());
		
		
		CarImage carImage = new CarImage();
		carImage.setId(updateCarImageRequest.getId());
		carImage.setImagePath(updateCarImageRequest.getImagePath());
		carImage.setCar(car);
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.UPDATE) ;
	}
	
	@Override
	public DataResult<List<CarImage>> getByCarId(int carId) {
		return new SuccessDataResult<List<CarImage>>(this.ifCarImageIsNullAddLogo(carId));

	}

	private Result checkIfCarImageLimitExceeded(int carId, int limit) {
		if (this.carImageDao.getByCar_CarId(carId).size() >= limit) {
			return new ErrorResult(Messages.Limit);
		}
		return new SuccessResult(Messages.Success);
	}
	
	private List<CarImage> ifCarImageIsNullAddLogo(int carId) {
		if (this.carImageDao.getByCar_CarId(carId).isEmpty()) {
						
		Car car = new Car();
		car.setCarId(carId);

		String imagePath = "carImages/logo.jpg";

		CarImage carImage = new CarImage();
		carImage.setCar(car);
		carImage.setImagePath(imagePath);
		
		List<CarImage> carImages=new ArrayList<CarImage>();
		carImages.add(carImage);
		
		return carImages;
		
		}
		return new ArrayList<CarImage>(this.carImageDao.getByCar_CarId(carId));
		
	}
}