package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.ReCapProject.dataAccess.abstracts.UserDao;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCorporateCustomerRequest;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
		
	private CorporateCustomerDao corporateCustomerDao;
	private UserDao userDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao,UserDao userDao,ModelMapper modelMapper) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.userDao = userDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CorporateCustomerDetailDto>> getAll() {
		List<CorporateCustomer> corporateCustomers= this.corporateCustomerDao.findAll();
		 
		 List<CorporateCustomerDetailDto> corporateCustomerDetailDto=corporateCustomers.stream().map(corporateCustomer -> modelMapper.map(corporateCustomer, CorporateCustomerDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerDetailDto>>(corporateCustomerDetailDto);
	}

	@Override
	public DataResult<CorporateCustomerDetailDto> getById(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(id);
		CorporateCustomerDetailDto corporateCustomerDetailDto = modelMapper.map(corporateCustomer,CorporateCustomerDetailDto.class);
		
		return new SuccessDataResult<CorporateCustomerDetailDto>(corporateCustomerDetailDto);
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		
		var result = BusinessRules.run(checkToEmailForRegister(createCorporateCustomerRequest.getEmail()),
				checkToPasswordForRegister(createCorporateCustomerRequest.getPassword()));

		if (result != null) {
			return result;
		}
			
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setTaxNumber(createCorporateCustomerRequest.getTaxNumber());
		corporateCustomer.setCompanyName(createCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setEmail(createCorporateCustomerRequest.getEmail());
		corporateCustomer.setPassword(createCorporateCustomerRequest.getPassword());
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
	
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setId(deleteCorporateCustomerRequest.getId());
		
		
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.CustomerDeleted);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		
		
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());
		corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setEmail(updateCorporateCustomerRequest.getEmail());
		corporateCustomer.setPassword(updateCorporateCustomerRequest.getPassword());
		corporateCustomer.setId(updateCorporateCustomerRequest.getId());
			
		
		this.corporateCustomerDao.save(corporateCustomer);
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
