package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.MaintenanceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.dataAccess.abstracts.MaintenanceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.requests.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateMaintenanceRequest;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceDao maintenanceDao;
	private RentalDao rentalDao;
	
	
	
	@Autowired
	public MaintenanceManager(MaintenanceDao maintenanceDao, RentalDao rentalDao) {
		super();
		this.maintenanceDao = maintenanceDao;
		this.rentalDao = rentalDao;
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
		
		var result = BusinessRules.run(checkIsTheCarRented(createMaintenanceRequest.getCarId()));
				

		if (result != null) {
			return result;
		}
		
		Car car = new Car();
		car.setCarId(createMaintenanceRequest.getCarId());
		
		
		Maintenance maintenance = new Maintenance();
		maintenance.setMaintenanceDate(createMaintenanceRequest.getMaintenanceDate());
		maintenance.setReturnDate(createMaintenanceRequest.getReturnDate());
		
		maintenance.setCar(car);
		
		
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(Messages.ADD);
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		maintenance.setMaintenanceId(deleteMaintenanceRequest.getMaintenanceId());
		
		this.maintenanceDao.delete(maintenance);
		return new SuccessResult(Messages.DELETE);
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
		return new SuccessResult(Messages.UPDATE);
	}
	
	private Result checkIsTheCarRented(int carId) {
		if(!this.rentalDao.getByCar_CarId(carId).isEmpty()) {
			return new ErrorResult(Messages.NotAvailableCar);
		}
		return new SuccessResult();
	}
	

}
