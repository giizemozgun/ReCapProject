package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;

import com.etiya.ReCapProject.entities.requests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCorporateCustomerRequest;


public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomer>> getAll();
	DataResult<CorporateCustomer> getById(int id);
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
}
