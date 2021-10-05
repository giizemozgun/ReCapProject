package com.etiya.ReCapProject.business.concretes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.constants.FilePathConfiguration;
import com.etiya.ReCapProject.core.utilities.helpers.FileHelper;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.dtos.CarImageDetailDto;
import com.etiya.ReCapProject.entities.requests.carImage.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.carImage.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.carImage.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private ModelMapper modelMapper;
	private CarService carService;

	@Autowired
	public CarImageManager(CarImageDao carImageDao,ModelMapper modelMapper,CarService carService) {
		super();
		this.carImageDao = carImageDao;
		this.modelMapper = modelMapper;
		this.carService = carService;
	}

	@Override
    public DataResult<List<CarImageDetailDto>> getAll() {
        List<CarImage> carImages= this.carImageDao.findAll();

         List<CarImageDetailDto> carImageDetailDtos = new ArrayList<CarImageDetailDto>();

        for (CarImage carImage : carImages) {
            CarImageDetailDto carImageDetailDto = modelMapper.map(carImage, CarImageDetailDto.class);
            carImageDetailDto.setCarName(this.carService.getById(carImage.getCar().getCarId()).getData().getCarName());

            carImageDetailDtos.add(carImageDetailDto);
        }

        return new SuccessDataResult<List<CarImageDetailDto>>(carImageDetailDtos);
    }

	@Override
	public DataResult<CarImageDetailDto> getById(int id) {
		CarImage carImage = this.carImageDao.getById(id);
		CarImageDetailDto carImageDetailDto = modelMapper.map(carImage,CarImageDetailDto.class);
		carImageDetailDto.setCarName(this.carService.getById(carImage.getCar().getCarId()).getData().getCarName());
		
		return new SuccessDataResult<CarImageDetailDto>(carImageDetailDto);
	}
	
	@Override
	public DataResult<List<CarImageDetailDto>> getByCarId(int carId) {
		List<CarImage> carImages= this.ifCarImageIsNullAddLogo(carId);
		 
		List<CarImageDetailDto> carImageDetailDtos = new ArrayList<CarImageDetailDto>();
		 for (CarImage carImage : carImages) {
	            CarImageDetailDto carImageDetailDto = modelMapper.map(carImage, CarImageDetailDto.class);
	            carImageDetailDto.setCarName(this.carService.getById(carImage.getCar().getCarId()).getData().getCarName());

	            carImageDetailDtos.add(carImageDetailDto);
	        }
		return new SuccessDataResult<List<CarImageDetailDto>>(carImageDetailDtos);
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {

		var result = BusinessRules.run(checkIfCarImageLimitExceeded(createCarImageRequest.getCarId(), 5),
				checkImageType(createCarImageRequest.getFile()), checkCarImageIsNull(createCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Car car = modelMapper.map(createCarImageRequest, Car.class);

		LocalDate date = LocalDate.now();
		
        File imagePath=new FileHelper().createFile(createCarImageRequest.getFile());
		
        CarImage carImage = modelMapper.map(createCarImageRequest, CarImage.class);
		carImage.setCar(car);
		carImage.setDate(date);
		carImage.setImagePath(imagePath.toString());

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded);
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		 CarImage carImage = modelMapper.map(deleteCarImageRequest, CarImage.class);

		this.carImageDao.delete(carImage);
		return new SuccessResult(Messages.CarImageDeleted);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {

		var result = BusinessRules.run(checkIfCarImageLimitExceeded(updateCarImageRequest.getCarId(), 5),
				checkImageType(updateCarImageRequest.getFile()), checkCarImageIsNull(updateCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		LocalDate date = LocalDate.now();
		
	    File imagePath=new FileHelper().createFile(updateCarImageRequest.getFile());
	    
	    CarImage carImage = modelMapper.map(updateCarImageRequest, CarImage.class);
		carImage.setImagePath(imagePath.toString());
		carImage.setDate(date);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated);
	}

	private Result checkIfCarImageLimitExceeded(int carId, int limit) {
		if (this.carImageDao.getByCar_CarId(carId).size() >= limit) {
			return new ErrorResult(Messages.Limit);
		}
		return new SuccessResult();
	}

	private Result checkCarImageIsNull(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0)
			return new ErrorResult();

		return new SuccessResult();

	}

	private Result checkImageType(MultipartFile file) {

		if (checkCarImageIsNull(file).isSuccess()) {

			if (!file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("png")
				&& !file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("jpeg")) {
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

			CarImage carImage = new CarImage();
			carImage.setCar(car);
			carImage.setImagePath(FilePathConfiguration.CAR_IMAGE_DEFAULT_PATH);

			List<CarImage> carImages = new ArrayList<CarImage>();
			carImages.add(carImage);

			return carImages;

		}
		return new ArrayList<CarImage>(this.carImageDao.getByCar_CarId(carId));
	}
	


}