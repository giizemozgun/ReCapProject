package com.etiya.ReCapProject.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.PaymentService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.PaymentDao;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.concretes.Payment;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreatePaymentRequest;

@Service
public class PaymentManager implements PaymentService {
	
	private PaymentDao paymentDao;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao) {
		super();
		this.paymentDao = paymentDao;
	}

	@Override
	public DataResult<List<Payment>> getAll() {
		
		return new SuccessDataResult<List<Payment>>(this.paymentDao.findAll());
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		Rental rental = new Rental();
		rental.setId(createPaymentRequest.getRentalId());
		
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardId(createPaymentRequest.getCreditCardId());
		
		Payment payment = new Payment();
		payment.setCreditCard(creditCard);
		payment.setRental(rental);
		
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PaymentReceived);
	}
	
}
