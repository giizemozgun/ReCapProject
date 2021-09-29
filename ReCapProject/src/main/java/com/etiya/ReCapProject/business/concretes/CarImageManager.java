package com.etiya.ReCapProject.business.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.constants.FilePathConfiguration;
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
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		
		var result = BusinessRules.run(checkIfCarImageLimitExceeded(createCarImageRequest.getCarId(),5),
				checkImageType(createCarImageRequest.getFile()),checkCarImageIsNull(createCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}
			
		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());
		
		LocalDate date = LocalDate.now();
		
		String imagePath = UUID.randomUUID().toString();
		
		File myFile = new File(FilePathConfiguration.CAR_IMAGES_PATH + imagePath + "." + 
		createCarImageRequest.getFile().getContentType().substring(createCarImageRequest.getFile().getContentType().indexOf("/")+1));
		myFile.createNewFile();
		
		FileOutputStream fileOutputStream = new FileOutputStream(myFile);
		fileOutputStream.write(createCarImageRequest.getFile().getBytes());	
		fileOutputStream.close();
		
		
		CarImage carImage = new CarImage();
		carImage.setCar(car);
		carImage.setDate(date);
		carImage.setImagePath(myFile.toString());
				
		
		
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded) ;
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		Car car = new Car();
		car.setCarId(deleteCarImageRequest.getCarId());
		
		
		CarImage carImage = new CarImage();
		
		carImage.setId(deleteCarImageRequest.getId());
		carImage.setCar(car);
		
		this.carImageDao.delete(carImage);
		return new SuccessResult(Messages.CarImageDeleted);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		
		LocalDate date = LocalDate.now();
		String imagePath = UUID.randomUUID().toString();
		
		File myFile = new File(FilePathConfiguration.CAR_IMAGES_PATH + imagePath + "." + updateCarImageRequest.getFile().getContentType()
				.substring(updateCarImageRequest.getFile().getContentType().indexOf("/")+1));
		myFile.createNewFile();
		
		FileOutputStream fileOutpuStream = new FileOutputStream(myFile);
		fileOutpuStream.write(updateCarImageRequest.getFile().getBytes());
		fileOutpuStream.close();
		
		Car car = new Car();
		car.setCarId(updateCarImageRequest.getCarId());
		
		CarImage carImage = new CarImage();
		carImage.setImagePath(myFile.toString());
		carImage.setDate(date);
		carImage.setCar(car);
				
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated) ;
	}
	
	@Override
	public DataResult<List<CarImage>> getByCarId(int carId) {
		return new SuccessDataResult<List<CarImage>>(this.ifCarImageIsNullAddLogo(carId));

	}

	private Result checkIfCarImageLimitExceeded(int carId, int limit) {
		if (this.carImageDao.getByCar_CarId(carId).size() >= limit) {
			return new ErrorResult(Messages.Limit);
		}
		return new SuccessResult();
	}
	
	private Result checkCarImageIsNull(MultipartFile file) {
		if(file == null || file.isEmpty() || file.getSize() == 0)
			return new ErrorResult();
		
		return new SuccessResult();	
		
		}
	private Result checkImageType(MultipartFile file) {
		
		if(checkCarImageIsNull(file).isSuccess()) {
			
		if(!file.getContentType().substring(file.getContentType().indexOf("/")+1).equals("png")) {
			System.out.println(file.getContentType());
			return new ErrorResult(Messages.FormatError);
			}
		
		}
		
		return new SuccessResult();
	}
	
	private List<CarImage> ifCarImageIsNullAddLogo(int carId) {
		if (this.carImageDao.getByCar_CarId(carId).isEmpty()) {

			Car car = new Car();
			car.setCarId(carId);

			CarImage carImage=new CarImage();
			carImage.setCar(car);
			carImage.setImagePath(FilePathConfiguration.CAR_IMAGE_DEFAULT_PATH);
	
			List<CarImage> carImages=new ArrayList<CarImage>();
			carImages.add(carImage);
			
			return carImages;
		    
		}
		return new ArrayList<CarImage>(this.carImageDao.getByCar_CarId(carId));
	}
	
	
}