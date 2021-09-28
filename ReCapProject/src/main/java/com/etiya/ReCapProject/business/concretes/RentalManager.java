package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.FindexPointService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.MaintenanceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.Customer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private FindexPointService findexPointService;
	private CarService carService;
	private IndividualCustomerDao individualCustomerDao;
	private CorporateCustomerDao corporateCustomerDao;
	private MaintenanceDao maintenanceDao;


	@Autowired
	public RentalManager(RentalDao rentalDao, FindexPointService findexPointService, CarService carService,
			IndividualCustomerDao individualCustomerDao, CorporateCustomerDao corporateCustomerDao,
			MaintenanceDao maintenanceDao) {
		super();
		this.rentalDao = rentalDao;
		this.findexPointService = findexPointService;
		this.carService = carService;
		this.corporateCustomerDao = corporateCustomerDao;
		this.individualCustomerDao = individualCustomerDao;
		this.maintenanceDao = maintenanceDao;
		
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll());
	}
	

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(rentalId));
	}

	public Result addForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		
		Car car=this.carService.getById(createRentalRequest.getCarId()).getData();
		
		
		IndividualCustomer customer=new IndividualCustomer();
		customer.setId(createRentalRequest.getCustomerId());
		
		Rental rental=new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(createRentalRequest.getReturnLocation());
		
		car.setCity(createRentalRequest.getReturnLocation());
		
		var result = BusinessRules.run(checkReturn(rental.getCar().getCarId()), 
				checkFindexPointForIndividualCustomer(this.individualCustomerDao.getById(createRentalRequest.getCustomerId()),
						createRentalRequest.getCarId()),checkIsTheCarInMaintenance(createRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL + " " +  Messages.ADD);
		
	}
	@Override
	public Result addForCorporateCustomer(CreateRentalRequest createRentalRequest) {
			
		Car car=this.carService.getById(createRentalRequest.getCarId()).getData();
		
		CorporateCustomer customer=new CorporateCustomer();
		customer.setId(createRentalRequest.getCustomerId());
		
		Rental rental=new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(createRentalRequest.getReturnLocation());
		
		car.setCity(createRentalRequest.getReturnLocation());
		
		var result = BusinessRules.run(checkReturn(rental.getCar().getCarId()), 
				checkFindexPointForCorporateCustomer(this.corporateCustomerDao.getById(createRentalRequest.getCustomerId()),
						createRentalRequest.getCarId()),checkIsTheCarInMaintenance(createRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL + " " +  Messages.ADD);
		
	}
	
	@Override
	public Result updateForCorporateCustomer(UpdateRentalRequest updateRentalRequest) {
		Car car=new Car();
		car.setCarId(updateRentalRequest.getCarId());

		
		
		Customer customer=new Customer();
		customer.setId(updateRentalRequest.getCustomerId());
		
		Rental rental=new Rental();
		rental.setId(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);		
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL + " " + Messages.UPDATE);
	}
	

	@Override
	public Result updateForIndividualCustomer(UpdateRentalRequest updateRentalRequest) {
		Car car=new Car();
		car.setCarId(updateRentalRequest.getCarId());
		
		Customer customer=new Customer();
		customer.setId(updateRentalRequest.getCustomerId());
		
		Rental rental=new Rental();
		rental.setId(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);		
		
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTAL + " " + Messages.UPDATE);
	}

	

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Car car=new Car();
		car.setCarId(deleteRentalRequest.getCarId());

		Rental rental=new Rental();
		rental.setId(deleteRentalRequest.getId());
		rental.setCar(car);
		
		
		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RENTAL + " " +Messages.DELETE);
	}

	private Result checkReturn(int carId) {         
		for (Rental rental : this.rentalDao.getByCar_CarId(carId)) {              
			
			if (rental.getReturnDate()==null) {                 
				return new ErrorResult(Messages.NOT_DELİVERED);             
				}         
			}         
		return new SuccessResult();     
		}

	
	
	private Result checkFindexPointForIndividualCustomer(IndividualCustomer individualCustomer,int carId) {  
		
	var findexScoreResult=this.findexPointService.getIndividualCustomerFindexPoint(individualCustomer.getIdentityNumber()); 
	
	var minFindexScoreForCar=this.carService.getById(carId).getData().getMinFindexScore();
	
	if (findexScoreResult <= minFindexScoreForCar) {
		
		return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);         
		}                  
	return new SuccessResult();     
	}
	
	
	private Result checkFindexPointForCorporateCustomer(CorporateCustomer corporateCustomer,int carId) {  
		
		var findexScoreResult=this.findexPointService.getCorporateCustomerFindexPoint(corporateCustomer.getTaxNumber()); 
		
		var minFindexScoreForCar=this.carService.getById(carId).getData().getMinFindexScore();
		
		if (findexScoreResult <= minFindexScoreForCar) {             
			return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);         
			}                  
		return new SuccessResult();     
		}
		
	private Result checkIsTheCarInMaintenance(int carId) {
		if(this.maintenanceDao.getByCar_CarId(carId).size() != 0) {
		Maintenance maintenance = this.maintenanceDao.getByCar_CarId(carId)
				.get(this.maintenanceDao.getByCar_CarId(carId).size()-1);
		
		if(maintenance.getReturnDate()==null) {
			return new ErrorResult(Messages.InCarMaintenance);
		}
		}
		return new SuccessResult();
	}
	 
	
}