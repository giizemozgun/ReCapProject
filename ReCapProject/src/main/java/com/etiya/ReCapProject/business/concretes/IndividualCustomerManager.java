package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.UserDao;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerDao individualCustomerDao;
	private UserDao userDao;
	private ModelMapper modelMapper;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, UserDao userDao,ModelMapper modelMapper) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.userDao = userDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<IndividualCustomerDetailDto>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
		 
		 List<IndividualCustomerDetailDto> individualCustomerDetailDtos =individualCustomers.stream().map(individualCustomer -> modelMapper.map(individualCustomer, IndividualCustomerDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerDetailDto>>(individualCustomerDetailDtos);
	}

	@Override
	public DataResult<IndividualCustomerDetailDto> getById(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
		IndividualCustomerDetailDto individualCustomerDetailDto = modelMapper.map(individualCustomer,IndividualCustomerDetailDto.class);
		
		return new SuccessDataResult<IndividualCustomerDetailDto>(individualCustomerDetailDto);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		var result = BusinessRules.run(checkToEmailForRegister(createIndividualCustomerRequest.getEmail()),
				checkToPasswordForRegister(createIndividualCustomerRequest.getPassword()));

		if (result != null) {
			return result;
		}
		
		IndividualCustomer individualCustomer=new IndividualCustomer();
		individualCustomer.setEmail(createIndividualCustomerRequest.getEmail());
		individualCustomer.setPassword(createIndividualCustomerRequest.getPassword());
		individualCustomer.setFirstName(createIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(createIndividualCustomerRequest.getLastName());
		individualCustomer.setIdentityNumber(createIndividualCustomerRequest.getIdentityNumber());
		
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer=new IndividualCustomer();
		individualCustomer.setId(deleteIndividualCustomerRequest.getId());
		
		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult(Messages.CustomerDeleted);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		
		IndividualCustomer individualCustomer=new IndividualCustomer();
		individualCustomer.setId(updateIndividualCustomerRequest.getId());
		individualCustomer.setEmail(updateIndividualCustomerRequest.getEmail());
		individualCustomer.setPassword(updateIndividualCustomerRequest.getPassword());
		individualCustomer.setFirstName(updateIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(updateIndividualCustomerRequest.getLastName());
		individualCustomer.setIdentityNumber(updateIndividualCustomerRequest.getIdentityNumber());
		
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CustomerUpdated);
	}
	
	private Result checkToEmailForRegister(String email) {

		if (this.userDao.existsByEmail(email)) {
			return new ErrorResult(Messages.ExistsUser);
		}
		return new SuccessResult();

	}
	private Result checkToPasswordForRegister(String password) {

		if (this.userDao.existsByPassword(password))  {
			return new ErrorResult(Messages.ERROR);
		}
		return new SuccessResult();

	}
}
