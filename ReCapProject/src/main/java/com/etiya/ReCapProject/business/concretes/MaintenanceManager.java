package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.MaintenanceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.dataAccess.abstracts.MaintenanceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.dtos.MaintenanceDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CarReturnedFromMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.maintenance.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.maintenance.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.maintenance.UpdateMaintenanceRequest;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceDao maintenanceDao;
	private RentalDao rentalDao;
	private CarDao carDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public MaintenanceManager(MaintenanceDao maintenanceDao, RentalDao rentalDao,CarDao carDao,ModelMapper modelMapper) {
		super();
		this.maintenanceDao = maintenanceDao;
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public DataResult<List<MaintenanceDetailDto>> getAll() {
		
		List<Maintenance> maintenances= this.maintenanceDao.findAll();
		
		List<MaintenanceDetailDto> maintenanceDetailDtos =maintenances.stream().map(maintenance -> modelMapper.map(maintenance, MaintenanceDetailDto.class)).collect(Collectors.toList());
	        return new SuccessDataResult<List<MaintenanceDetailDto>>(maintenanceDetailDtos, Messages.MaintenancesListed);
	}

	@Override
	public DataResult<MaintenanceDetailDto> getById(int id) {
		
		Maintenance maintenance= this.maintenanceDao.getById(id);
		MaintenanceDetailDto maintenanceDetailDto=modelMapper.map(maintenance, MaintenanceDetailDto.class);
		maintenanceDetailDto.setCarName(this.carDao.getById(maintenance.getCar().getCarId()).getCarName());
		
		return new SuccessDataResult<MaintenanceDetailDto>(maintenanceDetailDto, Messages.GetMaintenance);
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		
		var result = BusinessRules.run(checkReturnFromRental(createMaintenanceRequest.getCarId()));
				

		if (result != null) {
			return result;
		}
		
		Car car = this.carDao.getById(createMaintenanceRequest.getCarId());
		car.setAvailable(false);

		Maintenance maintenance = modelMapper.map(createMaintenanceRequest, Maintenance.class);
		
		this.maintenanceDao.save(maintenance);
		this.carDao.save(car);
		return new SuccessResult(Messages.MaintenanceAdded);
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		
		Maintenance maintenance = modelMapper.map(deleteMaintenanceRequest, Maintenance.class);
		
		this.maintenanceDao.delete(maintenance);
		return new SuccessResult(Messages.MaintenanceDeleted);
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		
		Maintenance maintenance = modelMapper.map(updateMaintenanceRequest, Maintenance.class);
		
		
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(Messages.MaintenanceUpdated);
	}
	@Override
	public Result validateCarReturned(CarReturnedFromMaintenanceRequest carReturnedFromMaintenanceRequest) {
		
		Maintenance maintenance = this.maintenanceDao.getById(carReturnedFromMaintenanceRequest.getMaintenanceId());
		maintenance.setCarReturned(true);
		
		Car car = this.carDao.getById(maintenance.getCar().getCarId());
		car.setAvailable(true);
		
		maintenance.setCar(car);
		this.maintenanceDao.save(maintenance);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.CarIsReturnedFromMaintenance);
			
	}

	private Result checkReturnFromRental(int carId) {
		if (this.rentalDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.NotAvailableCar);
		}
		return new SuccessResult();

	}

	
	

}