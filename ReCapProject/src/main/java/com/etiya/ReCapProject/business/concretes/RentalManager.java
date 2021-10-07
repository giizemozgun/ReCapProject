package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.abstracts.FindexPointService;
import com.etiya.ReCapProject.business.abstracts.PosService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.MaintenanceDao;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.AdditionalServiceForRentalDto;
import com.etiya.ReCapProject.entities.dtos.CreditCardDetailDto;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.car.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.creditCard.PosServiceRequest;
import com.etiya.ReCapProject.entities.requests.rental.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.rental.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.rental.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private FindexPointService findexPointService;
	private MaintenanceDao maintenanceDao;
	private CarDao carDao;
	private PosService posService;
	private CorporateCustomerDao corporateCustomerDao;
	private IndividualCustomerDao individualCustomerDao;
	private CreditCardService creditCardService;
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapper modelMapper;

	@Autowired
	public RentalManager(RentalDao rentalDao, FindexPointService findexPointService, CarDao carDao,
			MaintenanceDao maintenanceDao, PosService posService, CorporateCustomerDao corporateCustomerDao,
			IndividualCustomerDao individualCustomerDao, CreditCardService creditCardService,
			AdditionalServiceDao additionalServiceDao, ModelMapper modelMapper) {
		super();
		this.rentalDao = rentalDao;
		this.findexPointService = findexPointService;
		this.maintenanceDao = maintenanceDao;
		this.carDao = carDao;
		this.posService = posService;
		this.corporateCustomerDao = corporateCustomerDao;
		this.individualCustomerDao = individualCustomerDao;
		this.creditCardService = creditCardService;
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<RentalDetailDto>> getAll() {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		List<Rental> rentals = this.rentalDao.findAll();
		List<RentalDetailDto> rentalDetailDtos = rentals.stream()
				.map(rental -> modelMapper.map(rental, RentalDetailDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<RentalDetailDto>>(rentalDetailDtos, Messages.RentalsListed);
	}

	@Override
	public DataResult<RentalDetailDto> getById(int id) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		Rental rental = this.rentalDao.getById(id);
		RentalDetailDto rentalDetailDto = modelMapper.map(rental, RentalDetailDto.class);

		return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, Messages.GetRental);
	}

	@Override
	public Result addForIndividualCustomer(CreateRentalRequest createRentalRequest) {

		IndividualCustomer customer = this.individualCustomerDao.getById(createRentalRequest.getCustomerId());

		var result = BusinessRules.run(checkReturnFromRental(createRentalRequest.getCarId()),
				checkReturnFromMaintenance(createRentalRequest.getCarId()),
				checkFindexPointForIndividualCustomer(customer, createRentalRequest.getCarId()),
				this.creditCardService.checkCreditCardFormatAndId(createRentalRequest.getCreditCardDetailDto(), createRentalRequest.getCreditCardId()),
				isCreditCardLimitExceeded(createRentalRequest.getCreditCardDetailDto(),
						this.calculateTotalAmount(createRentalRequest, 500)));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(createRentalRequest.getCarId());

		Rental rental = modelMapper.map(createRentalRequest, Rental.class);
		rental.setPickUpLocation(car.getCity());
		rental.setPickUpKm(car.getKm());
		rental.setTotalAmount(this.calculateTotalAmount(createRentalRequest, 500));
		rental.setAdditionalServices(
				this.convertToAdditionalService(createRentalRequest.getAdditionalServiceForRentalDtos()));

	
		if (createRentalRequest.isShouldWeSaveCard()) {
			this.creditCardService.saveCardInformation(createRentalRequest.getCreditCardDetailDto(),
					createRentalRequest.getCustomerId());
		}
		
		this.rentalDao.save(rental);

		
		car.setAvailable(false);
		this.carDao.save(car);

		return new SuccessResult(Messages.RentalAdded);

	}

	@Override
	public Result addForCorporateCustomer(CreateRentalRequest createRentalRequest) {

		CorporateCustomer customer = this.corporateCustomerDao.getById(createRentalRequest.getCustomerId());

		var result = BusinessRules.run(checkReturnFromRental(createRentalRequest.getCarId()),
				checkReturnFromMaintenance(createRentalRequest.getCarId()), 
				checkFindexPointForCorporateCustomer(customer, createRentalRequest.getCarId()),
				this.creditCardService.checkCreditCardFormatAndId(createRentalRequest.getCreditCardDetailDto(),createRentalRequest.getCreditCardId()),
				isCreditCardLimitExceeded(createRentalRequest.getCreditCardDetailDto(),
						this.calculateTotalAmount(createRentalRequest, 500)));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(createRentalRequest.getCarId());

		Rental rental = modelMapper.map(createRentalRequest, Rental.class);
		rental.setPickUpLocation(car.getCity());
		rental.setPickUpKm(car.getKm());
		rental.setTotalAmount(this.calculateTotalAmount(createRentalRequest, 500));
		rental.setAdditionalServices(
				this.convertToAdditionalService(createRentalRequest.getAdditionalServiceForRentalDtos()));

		if (createRentalRequest.isShouldWeSaveCard()) {
			this.creditCardService.saveCardInformation(createRentalRequest.getCreditCardDetailDto(),
					createRentalRequest.getCustomerId());
		}
		
		this.rentalDao.save(rental);

		car.setAvailable(false);
		this.carDao.save(car);

		return new SuccessResult(Messages.RentalAdded);

	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {

		Rental rental = modelMapper.map(deleteRentalRequest, Rental.class);

		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RentalDeleted);
	}

	@Override
	public Result updateForIndividualCustomer(UpdateRentalRequest updateRentalRequest) {

		IndividualCustomer customer = new IndividualCustomer();
		customer.setId(updateRentalRequest.getCustomerId());

		var result = BusinessRules.run(checkFindexPointForIndividualCustomer(customer, updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(updateRentalRequest.getCarId());

		Rental rental = modelMapper.map(updateRentalRequest, Rental.class);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setPickUpKm(car.getKm());
		rental.setAdditionalServices(this.convertToAdditionalService(updateRentalRequest.getAdditionalServiceDtos()));

		rental.setCarReturned(true);
		this.rentalDao.save(rental);

		this.carDao.save(car);

		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result updateForCorporateCustomer(UpdateRentalRequest updateRentalRequest) {

		CorporateCustomer customer = modelMapper.map(updateRentalRequest, CorporateCustomer.class);

		var result = BusinessRules.run(checkFindexPointForCorporateCustomer(customer, updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(updateRentalRequest.getCarId());

		Rental rental = modelMapper.map(updateRentalRequest, Rental.class);
		rental.setCustomer(customer);
		rental.setPickUpLocation(car.getCity());
		rental.setPickUpKm(car.getKm());
		rental.setAdditionalServices(this.convertToAdditionalService(updateRentalRequest.getAdditionalServiceDtos()));

		rental.setCarReturned(true);
		this.rentalDao.save(rental);
		
		this.carDao.save(car);

		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result validateCarReturned(CarReturnedRequest carReturnedRequest) {
		Rental rental = this.rentalDao.getById(carReturnedRequest.getRentalId());
		rental.setCarReturned(true);
		rental.setReturnKm(carReturnedRequest.getReturnKm());

		Car car = this.carDao.getById(rental.getCar().getCarId());
		car.setAvailable(true);
		car.setKm(carReturnedRequest.getReturnKm());
		car.setCity(rental.getReturnLocation());

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

		var minFindexScoreForCar = this.carDao.getById(carId).getMinFindexScore();

		if (findexScoreResult <= minFindexScoreForCar) {
			return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);
		}

		return new SuccessResult();
	}

	private Result checkFindexPointForCorporateCustomer(CorporateCustomer corporateCustomer, int carId) {
		var findexScoreResult = this.findexPointService
				.getCorporateCustomerFindexPoint(corporateCustomer.getTaxNumber());

		var minFindexScoreForCar = this.carDao.getById(carId).getMinFindexScore();

		if (findexScoreResult <= minFindexScoreForCar) {
			return new ErrorResult(Messages.CustomerCreditScoreNotEnoughtToRentCar);
		}

		return new SuccessResult();
	}

	private int calculateTotalAmount(CreateRentalRequest createRentalRequest, double cityChangeFee) {
		Car car = this.carDao.getById(createRentalRequest.getCarId());

		long totalRentalDay = ChronoUnit.DAYS.between(createRentalRequest.getRentDate().toInstant(),
				createRentalRequest.getReturnDate().toInstant());

		double totalAmount = car.getDailyPrice() * totalRentalDay;

		if (createRentalRequest.getReturnLocation().equals(car.getCity())) {
			totalAmount += cityChangeFee;
		}

		for (AdditionalServiceForRentalDto additionalServiceDto : createRentalRequest
				.getAdditionalServiceForRentalDtos()) {

			AdditionalService additionalService = this.additionalServiceDao.getById(additionalServiceDto.getId());

			double additionalServiceTotalPrice = additionalService.getDailyPrice() * totalRentalDay;

			totalAmount += additionalServiceTotalPrice;
		}

		return (int) totalAmount;
	}

	private Result isCreditCardLimitExceeded(CreditCardDetailDto creditCardDetailDto, double totalAmount) {

		PosServiceRequest posServiceRequest = modelMapper.map(creditCardDetailDto, PosServiceRequest.class);
		posServiceRequest.setPaymentAmount(totalAmount);

		if (!this.posService.isCreditCardLimitExceeded(posServiceRequest)) {
			return new ErrorResult(Messages.CreditCardLimitExceeded);
		}
		return new SuccessResult();
	}

	private List<AdditionalService> convertToAdditionalService(

			List<AdditionalServiceForRentalDto> additionalServiceForRentalDtos) {
		List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();

		for (AdditionalServiceForRentalDto additionalServicedto : additionalServiceForRentalDtos) {
			additionalServices.add(this.additionalServiceDao.getById(additionalServicedto.getId()));
		}

		return additionalServices;
	}

}