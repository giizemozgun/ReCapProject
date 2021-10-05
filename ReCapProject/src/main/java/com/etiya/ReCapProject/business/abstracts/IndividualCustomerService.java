package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.individualCustomer.UpdateIndividualCustomerRequest;


public interface IndividualCustomerService {
	
	DataResult<List<IndividualCustomerDetailDto>> getAll();
	DataResult<IndividualCustomerDetailDto> getById(int id);
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	
}
