package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.AdditionalServiceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import com.etiya.ReCapProject.entities.requests.create.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateAdditionalServiceRequest;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{

	private AdditionalServiceDao additionalServiceDao;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao) {
		super();
		this.additionalServiceDao = additionalServiceDao;
	}
	
	@Override
	public DataResult<List<AdditionalService>> getAll() {
		return new SuccessDataResult<List<AdditionalService>>(this.additionalServiceDao.findAll());
	}

	@Override
	public DataResult<AdditionalService> getById(int id) {
		return new SuccessDataResult<AdditionalService>(this.additionalServiceDao.getById(id));
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		var result = BusinessRules.run(checkAdditionalServiceNameDuplication(createAdditionalServiceRequest.getName()));

		if (result != null) {
			return result;
		}
		
		AdditionalService additionalService = new AdditionalService();
		additionalService.setName(createAdditionalServiceRequest.getName());
		additionalService.setDailyPrice(createAdditionalServiceRequest.getDailyPrice());
		additionalService.setDescription(createAdditionalServiceRequest.getDescription());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.AdditionalServiceAdded);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		AdditionalService additionalService = new AdditionalService();
		additionalService.setId(deleteAdditionalServiceRequest.getId());
		
		this.additionalServiceDao.delete(additionalService);
		return new SuccessResult(Messages.AdditionalServiceDeleted);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		AdditionalService additionalService = new AdditionalService();
		additionalService.setId(updateAdditionalServiceRequest.getId());
		additionalService.setName(updateAdditionalServiceRequest.getName());
		additionalService.setDailyPrice(updateAdditionalServiceRequest.getDailyPrice());
		additionalService.setDescription(updateAdditionalServiceRequest.getDescription());
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.AdditionalServiceUpdated);
	}
	
	private Result checkAdditionalServiceNameDuplication(String name) {

		if (this.additionalServiceDao.existsByName(name)) {
			return new ErrorResult(Messages.ExistAdditionalService);
		}
		return new SuccessResult();

	}
	

	
}
