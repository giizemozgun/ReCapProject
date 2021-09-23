package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.requests.CreateCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCreditCardRequest;

public interface CreditCardService {
	
	DataResult<List<CreditCard>> getAll();
	DataResult<CreditCard> getById(int creditCardId);
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
		
	DataResult<List<CreditCard>> getByCustomerId(int customerId);
}	
