package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.abstracts.FindexPointService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.PosService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
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
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.CreditCardDetailDto;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.CreateCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.PosServiceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private FindexPointService findexPointService;
	private CarService carService;
	private MaintenanceDao maintenanceDao;
	private CarDao carDao;
	private PosService posService;
	private CorporateCustomerService corporateCustomerService;
	private IndividualCustomerService individualCustomerService;
	private CreditCardService creditCardService;

	@Autowired
	public RentalManager(RentalDao rentalDao, FindexPointService findexPointService, CarService carService,
			MaintenanceDao maintenanceDao, CarDao carDao, PosService posService
			,CorporateCustomerService corporateCustomerService,IndividualCustomerService individualCustomerService,
			CreditCardService creditCardService) {
		super();
		this.rentalDao = rentalDao;
		this.findexPointService = findexPointService;
		this.carService = carService;
		this.maintenanceDao = maintenanceDao;
		this.carDao = carDao;
		this.posService = posService;	
		this.corporateCustomerService = corporateCustomerService;
		this.individualCustomerService = individualCustomerService;
		this.creditCardService = creditCardService;
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll());
	}

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(rentalId));
	}

	@Override
	public Result addForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		
		IndividualCustomer customer = this.individualCustomerService.getById(createRentalRequest.getCustomerId()).getData();
		
		var result = BusinessRules.run(checkReturnFromRental(createRentalRequest.getCarId()),
				checkFindexPointForIndividualCustomer(customer, createRentalRequest.getCarId()),
				checkReturnFromMaintenance(createRentalRequest.getCarId()),
				isCreditCardLimitExceeded(createRentalRequest.getCreditCardDetailDto(),this.calculateTotalAmount(createRentalRequest)));

		if (result != null) {
			return result;
		}
		
		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(createRentalRequest.getReturnLocation());
		rental.setPickUpKm(car.getKm());
		
		this.rentalDao.save(rental);
		
		if(createRentalRequest.isCardSaved()) {
			this.cardInformationSavedIfCardIsSavedIsTrue(createRentalRequest.getCreditCardDetailDto(), createRentalRequest.getCustomerId());
		}
		
		car.setCity(rental.getReturnLocation());
		car.setAvailable(false);
		this.carDao.save(car);
		
		
		return new SuccessResult(Messages.RentalAdded);

	}

	@Override
	public Result addForCorporateCustomer(CreateRentalRequest createRentalRequest) {
		

		CorporateCustomer customer =this.corporateCustomerService.getById(createRentalRequest.getCustomerId()).getData();
		
		
		
		var result = BusinessRules.run(checkReturnFromRental(createRentalRequest.getCarId()),
				checkFindexPointForCorporateCustomer(customer, createRentalRequest.getCarId()),
				checkReturnFromMaintenance(createRentalRequest.getCarId()),
				isCreditCardLimitExceeded(createRentalRequest.getCreditCardDetailDto(),this.calculateTotalAmount(createRentalRequest)));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();
		
		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(createRentalRequest.getReturnLocation());
		rental.setPickUpKm(car.getKm());
		
			
		
		this.rentalDao.save(rental);
		
		if(createRentalRequest.isCardSaved()) {
			this.cardInformationSavedIfCardIsSavedIsTrue(createRentalRequest.getCreditCardDetailDto(), createRentalRequest.getCustomerId());
		}
		
		
		car.setCity(rental.getReturnLocation());
		car.setAvailable(false);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RentalAdded);

	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());

		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RentalDeleted);
	}

	@Override
	public Result updateForIndividualCustomer(UpdateRentalRequest updateRentalRequest) {
		Car car = this.carService.getById(updateRentalRequest.getCarId()).getData();

		IndividualCustomer customer = new IndividualCustomer();
		customer.setId(updateRentalRequest.getCustomerId());

		Rental rental = new Rental();
		rental.setId(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(updateRentalRequest.getReturnLocation());
		rental.setPickUpKm(car.getKm());

		var result = BusinessRules.run(checkReturnFromRental(rental.getCar().getCarId()),
				checkFindexPointForIndividualCustomer(customer, updateRentalRequest.getCarId()),
				checkReturnFromMaintenance(updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}
	
		rental.setCarReturned(true);
		this.rentalDao.save(rental);
		
		car.setCity(rental.getReturnLocation());
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result updateForCorporateCustomer(UpdateRentalRequest updateRentalRequest) {
		Car car = this.carService.getById(updateRentalRequest.getCarId()).getData();

		CorporateCustomer customer = new CorporateCustomer();
		customer.setId(updateRentalRequest.getCustomerId());

		Rental rental = new Rental();
		rental.setId(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setReturnLocation(updateRentalRequest.getReturnLocation());
		rental.setPickUpKm(car.getKm());
		
		var result = BusinessRules.run(checkReturnFromRental(rental.getCar().getCarId()),
				checkFindexPointForCorporateCustomer(customer, updateRentalRequest.getCarId()),
				checkReturnFromMaintenance(updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}
		
		rental.setCarReturned(true);
		this.rentalDao.save(rental);
		
		car.setCity(rental.getReturnLocation());
		this.carDao.save(car);
		
		return new SuccessResult(Messages.RentalUpdated);
	}
	
	@Override
	public Result validateCarReturned(CarReturnedRequest carReturnedRequest) {
		Rental rental = this.rentalDao.getById(carReturnedRequest.getRentalId());
		rental.setCarReturned(true);
		rental.setReturnKm(carReturnedRequest.getReturnKm());
		
		Car car = this.carService.getById(rental.getCar().getCarId()).getData();
		car.setAvailable(true);
		car.setKm(carReturnedRequest.getReturnKm());
		
		
		rental.setCar(car);
		this.rentalDao.save(rental);
		this.carDao.save(car);
		return new SuccessResult(Messages.CarIsReturned);
	}

	private Result checkReturnFromRental(int carId) {
		if (this.rentalDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.NotAvailableCar);
		}
		return new SuccessResult();

	}

	private Result checkReturnFromMaintenance(int carId) {
		if (this.maintenanceDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.NotAvailableCar);
		}
		return new SuccessResult();

	}

	private Result checkFindexPointForIndividualCustomer(IndividualCustomer individualCustomer, int carId) {
		var findexScoreResult = this.findexPointService
				.getIndividualCustomerFindexPoint(individualCustomer.getIdentityNumber());

		var minFindexScoreForCar = this.carService.getById(carId).getData().getMinFindexScore();

		if (findexScoreResult <= minFindexScoreForCar) {
			return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);
		}

		return new SuccessResult();
	}

	private Result checkFindexPointForCorporateCustomer(CorporateCustomer corporateCustomer, int carId) {
		var findexScoreResult = this.findexPointService
				.getCorporateCustomerFindexPoint(corporateCustomer.getTaxNumber());

		var minFindexScoreForCar = this.carService.getById(carId).getData().getMinFindexScore();

		if (findexScoreResult <= minFindexScoreForCar) {
			return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);
		}

		return new SuccessResult();
	}

	private int calculateTotalAmount(CreateRentalRequest createRentalRequest) {
		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();
		long totalRentalDay = ChronoUnit.DAYS.between(createRentalRequest.getRentDate().toInstant(), createRentalRequest.getReturnDate().toInstant());
	    
		double totalAmount =car.getDailyPrice() * totalRentalDay;

		return (int)totalAmount ;
	}
	
	private Result isCreditCardLimitExceeded(CreditCardDetailDto creditCardDetailDto, double totalAmount) {
		
		PosServiceRequest posServiceRequest = new PosServiceRequest();
		posServiceRequest.setCardNumber(creditCardDetailDto.getCardNumber());
		posServiceRequest.setName(creditCardDetailDto.getName());
		posServiceRequest.setCvv(creditCardDetailDto.getCvv());
		posServiceRequest.setExpiryDate(creditCardDetailDto.getExpiryDate());
		posServiceRequest.setPaymentAmount(totalAmount);
		
		
		
		if(!this.posService.isCreditCardLimitExceeded(posServiceRequest) ) {
			return new ErrorResult(Messages.CreditCardLimitExceeded);
		}
		return new SuccessResult();
		
		
	}
	private Result cardInformationSavedIfCardIsSavedIsTrue(CreditCardDetailDto creditCardDetailDto, int customerId) {

		CreateCreditCardRequest createCreditCardRequest = new CreateCreditCardRequest();
		createCreditCardRequest.setName(creditCardDetailDto.getName());
		createCreditCardRequest.setCardNumber(creditCardDetailDto.getCardNumber());
		createCreditCardRequest.setExpiryDate(creditCardDetailDto.getExpiryDate());
		createCreditCardRequest.setCvv(creditCardDetailDto.getCvv());
		createCreditCardRequest.setCustomerId(customerId);

		return new SuccessResult(this.creditCardService.add(createCreditCardRequest).getMessage());
	}
	

	

	
}