package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import com.etiya.ReCapProject.entities.requests.create.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateAdditionalServiceRequest;

public interface AdditionalServiceService {
	
	DataResult<List<AdditionalService>> getAll();
	DataResult<AdditionalService> getById(int id);
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
}
