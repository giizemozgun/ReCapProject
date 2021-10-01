package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.requests.create.CreateCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCreditCardRequest;

public interface CreditCardService {
	
	DataResult<List<CreditCard>> getAll();
	DataResult<CreditCard> getById(int creditCardId);
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
		
	DataResult<List<CreditCard>> getByCustomerId(int customerId);
}	
