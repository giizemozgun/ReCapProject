package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.etiya.ReCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.etiya.ReCapProject.entities.requests.additionalService.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.additionalService.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.entities.requests.additionalService.UpdateAdditionalServiceRequest;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapper modelMapper;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,ModelMapper modelMapper) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<AdditionalServiceDetailDto>> getAll() {
		List<AdditionalService> additionalServices= this.additionalServiceDao.findAll();
		 
		 List<AdditionalServiceDetailDto> additionalServiceDetailDtos =additionalServices.stream().map(additionalService -> modelMapper.map(additionalService, AdditionalServiceDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceDetailDto>>(additionalServiceDetailDtos,Messages.AdditionalServicesListed);
	}

	@Override
	public DataResult<AdditionalServiceDetailDto> getById(int id) {
		AdditionalService additionalService = this.additionalServiceDao.getById(id);
		AdditionalServiceDetailDto additionalServiceDetailDto = modelMapper.map(additionalService,AdditionalServiceDetailDto.class);
		
		return new SuccessDataResult<AdditionalServiceDetailDto>(additionalServiceDetailDto,Messages.GetAdditionalService);
	}
	
	@Override
	public DataResult<List<AdditionalServiceDetailDto>> getByRentalId(int rentalId) {
		
		List<AdditionalService> additionalServices= this.additionalServiceDao.getByRentals_Id(rentalId);
		 
		 List<AdditionalServiceDetailDto> additionalServiceDetailDtos =additionalServices.stream().map(additionalService -> modelMapper.map(additionalService, AdditionalServiceDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceDetailDto>>(additionalServiceDetailDtos, Messages.AdditionalServicesOfRental);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
		var result = BusinessRules.run(checkAdditionalServiceNameDuplication(createAdditionalServiceRequest.getName()));

		if (result != null) {
			return result;
		}
		
		AdditionalService additionalService = modelMapper.map(createAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.AdditionalServiceAdded);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		AdditionalService additionalService = modelMapper.map(deleteAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.delete(additionalService);
		return new SuccessResult(Messages.AdditionalServiceDeleted);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		var result = BusinessRules.run(checkAdditionalServiceNameDuplication(updateAdditionalServiceRequest.getName()));

		if (result != null) {
			return result;
		}
		
		AdditionalService additionalService = modelMapper.map(updateAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.AdditionalServiceUpdated);
	}
	
	private Result checkAdditionalServiceNameDuplication(String name) {

		if (this.additionalServiceDao.existsByName(name)) {
			return new ErrorResult(Messages.ExistAdditionService);
		}
		return new SuccessResult();
	}
	
	
}
