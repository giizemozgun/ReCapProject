package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;

import com.etiya.ReCapProject.entities.requests.CreateIndividualCustomerRequest;

import com.etiya.ReCapProject.entities.requests.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateIndividualCustomerRequest;


public interface IndividualCustomerService {
	
	DataResult<List<IndividualCustomer>> getAll();
	DataResult<IndividualCustomer> getById(int id);
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	
}
