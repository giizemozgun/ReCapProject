package com.etiya.ReCapProject.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.abstracts.PaymentService;
import com.etiya.ReCapProject.business.abstracts.PosService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.PaymentDao;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.concretes.Payment;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreatePaymentRequest;
import com.etiya.ReCapProject.entities.requests.PosServiceRequest;

@Service
public class PaymentManager implements PaymentService {
	
	private PaymentDao paymentDao;
	private PosService posService;
	private CreditCardService creditCardService;

	
	@Autowired
	public PaymentManager(PaymentDao paymentDao,PosService posService,CreditCardService creditCardService) {
		super();
		this.paymentDao = paymentDao;
		this.posService = posService;
		this.creditCardService = creditCardService;
	
	}

	@Override
	public DataResult<List<Payment>> getAll() {
		
		return new SuccessDataResult<List<Payment>>(this.paymentDao.findAll());
	}
//
//	@Override
//	public Result add(CreatePaymentRequest createPaymentRequest) {

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		// TODO Auto-generated method stub
		return null;
	}
		
		
//		CreditCard creditCard = this.creditCardService.getById(createPaymentRequest.getCreditCardId()).getData();
//		
//		PosServiceRequest posServiceRequest = new PosServiceRequest();
//		posServiceRequest.setCreditCarId(creditCard.getCreditCardId());
//		posServiceRequest.setCardNumber(creditCard.getCardNumber());
//		posServiceRequest.setPaymentAmount(createPaymentRequest.getTotalAmount());
//		posServiceRequest.setCvv(creditCard.getCvv());
//		posServiceRequest.setExpiryDate(creditCard.getExpiryDate());
//		posServiceRequest.setName(creditCard.getName());
//		
//		
//		var result = BusinessRules.run(isCreditCardLimitExceeded(posServiceRequest));
//
//		if (result != null) {
//			return result;
//		}
//		
//		Rental rental = new Rental();
//		rental.setId(createPaymentRequest.getRentalId());
//		
//		Payment payment = new Payment();
//		payment.setCreditCard(creditCard);
//		payment.setRental(rental);
//	
//		this.paymentDao.save(payment);
//		return new SuccessResult(Messages.PaymentReceived);
//	}
////	
//	private Result isCreditCardLimitExceeded(PosServiceRequest posServiceRequest) {
//		
//		if(!this.posService.isCreditCardLimitExceeded(posServiceRequest) ) {
//			return new ErrorResult(Messages.CreditCardLimitExceeded);
//		}
//		return new SuccessResult();
//		
//		
//	}
	
	
	
	
	
}
