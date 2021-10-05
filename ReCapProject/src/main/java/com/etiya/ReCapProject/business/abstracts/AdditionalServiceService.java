package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.etiya.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

public interface AdditionalServiceService {

	DataResult<List<AdditionalServiceDetailDto>> getAll();
	DataResult<AdditionalServiceDetailDto> getById(int id);
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	
	DataResult<List<AdditionalServiceDetailDto>> getByRentalId(int rentalId);
	
	
}
