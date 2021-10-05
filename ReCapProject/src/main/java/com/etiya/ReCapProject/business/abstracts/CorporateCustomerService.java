package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.corporateCustomer.UpdateCorporateCustomerRequest;


public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomerDetailDto>> getAll();
	DataResult<CorporateCustomerDetailDto> getById(int id);
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
}
