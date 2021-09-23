package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CreditCardDao;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.concretes.Customer;
import com.etiya.ReCapProject.entities.requests.CreateCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCreditCardRequest;

@Service
public class CreditCardManager implements CreditCardService {
	
	private CreditCardDao creditCardDao;
	
	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao) {
		super();
		this.creditCardDao = creditCardDao;
	}

	@Override
	public DataResult<List<CreditCard>> getAll() {
		return new SuccessDataResult<List<CreditCard>>(this.creditCardDao.findAll());
	}

	@Override
	public DataResult<CreditCard> getById(int creditCardId) {
		return new SuccessDataResult<CreditCard>(this.creditCardDao.getById(creditCardId));
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		
		Customer customer = new Customer();
		customer.setId(createCreditCardRequest.getCustomerId());
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNumber(createCreditCardRequest.getCardNumber());
		creditCard.setName(createCreditCardRequest.getName());
		creditCard.setCvv(createCreditCardRequest.getCvv());
		creditCard.setExpiryDate(createCreditCardRequest.getExpiryDate());
		creditCard.setCustomer(customer);
		
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.ADD);
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardId(deleteCreditCardRequest.getCreditCardId());
		
		this.creditCardDao.delete(creditCard);
		return new SuccessResult(Messages.DELETE);
	}

	@Override
	public DataResult<List<CreditCard>> getByCustomerId(int customerId) {
		
		return new SuccessDataResult<List<CreditCard>>(this.creditCardDao.getByCustomer_id(customerId));
	}

}
