package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.MaintenanceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.dataAccess.abstracts.MaintenanceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.requests.CarReturnedFromMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateMaintenanceRequest;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceDao maintenanceDao;
	private RentalDao rentalDao;
	private CarService carService;
	private CarDao carDao;
	
	
	
	@Autowired
	public MaintenanceManager(MaintenanceDao maintenanceDao, RentalDao rentalDao, CarService carService,CarDao carDao) {
		super();
		this.maintenanceDao = maintenanceDao;
		this.rentalDao = rentalDao;
		this.carService = carService;
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<Maintenance>> getAll() {
		return new  SuccessDataResult<List<Maintenance>>(this.maintenanceDao.findAll());
	}

	@Override
	public DataResult<Maintenance> getById(int id) {
		return new SuccessDataResult<Maintenance>(this.maintenanceDao.getById(id));
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		
		var result = BusinessRules.run(checkReturnFromRental(createMaintenanceRequest.getCarId()));
				

		if (result != null) {
			return result;
		}
		
		Car car = this.carService.getById(createMaintenanceRequest.getCarId()).getData();
		car.setAvailable(false);
		
		
		Maintenance maintenance = new Maintenance();
		maintenance.setMaintenanceDate(createMaintenanceRequest.getMaintenanceDate());
		maintenance.setReturnDate(createMaintenanceRequest.getReturnDate());
		
		maintenance.setCar(car);
		
		
		this.maintenanceDao.save(maintenance);
		this.carDao.save(car);
		return new SuccessResult(Messages.MaintenanceAdded);
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		maintenance.setMaintenanceId(deleteMaintenanceRequest.getMaintenanceId());
		
		this.maintenanceDao.delete(maintenance);
		return new SuccessResult(Messages.MaintenanceDeleted);
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Car car = new Car();
		car.setCarId(updateMaintenanceRequest.getCarId());
		
		Maintenance maintenance = new Maintenance();
		maintenance.setMaintenanceId(updateMaintenanceRequest.getMaintenanceId());
		maintenance.setMaintenanceDate(updateMaintenanceRequest.getMaintenanceDate());
		maintenance.setReturnDate(updateMaintenanceRequest.getReturnDate());
		maintenance.setCar(car);
		
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(Messages.MaintenanceUpdated);
	}
	@Override
	public Result validateCarReturned(CarReturnedFromMaintenanceRequest carReturnedFromMaintenanceRequest) {
		
		Maintenance maintenance = this.maintenanceDao.getById(carReturnedFromMaintenanceRequest.getMaintenanceId());
		maintenance.setCarReturned(true);
		
		Car car = this.carService.getById(maintenance.getCar().getCarId()).getData();
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
