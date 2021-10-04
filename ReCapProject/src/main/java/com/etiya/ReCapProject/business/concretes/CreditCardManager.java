package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CreditCardDao;
import com.etiya.ReCapProject.entities.abstracts.Customer;
import com.etiya.ReCapProject.entities.concretes.CreditCard;
import com.etiya.ReCapProject.entities.dtos.CreditCardDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCreditCardRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCreditCardRequest;

@Service
public class CreditCardManager implements CreditCardService {
	
	private CreditCardDao creditCardDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapper modelMapper) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CreditCardDetailDto>> getAll() {
		List<CreditCard> creditCards = this.creditCardDao.findAll();
		 
		 List<CreditCardDetailDto> creditCardDetailDtos = creditCards.stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CreditCardDetailDto>>(creditCardDetailDtos);
	}

	@Override
	public DataResult<CreditCardDetailDto> getById(int creditCardId) {
		CreditCard creditCard = this.creditCardDao.getById(creditCardId);
		CreditCardDetailDto CreditCardDto = modelMapper.map(creditCard,CreditCardDetailDto.class);
		
		return new SuccessDataResult<CreditCardDetailDto>(CreditCardDto);
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		
		var result = BusinessRules.run(checkCreditCardNumber(createCreditCardRequest.getCardNumber()),
				checkCreditCardCvv(createCreditCardRequest.getCvv()), 
				checkCreditCardExpiryDate(createCreditCardRequest.getExpiryDate()));

		if (result != null) {
			return result;
		}
		
		Customer customer=new Customer();
		customer.setId(createCreditCardRequest.getCustomerId());

		CreditCard creditCard=new CreditCard();
		creditCard.setCardNumber(createCreditCardRequest.getCardNumber());
		creditCard.setName(createCreditCardRequest.getName());
		creditCard.setExpiryDate(createCreditCardRequest.getExpiryDate());
		creditCard.setCvv(createCreditCardRequest.getCvv());
		creditCard.setCustomer(customer);
		
		this.creditCardDao.save(creditCard);
		
		return new SuccessResult(Messages.CreditCardAdded);
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardId(deleteCreditCardRequest.getCreditCardId());
		
		this.creditCardDao.delete(creditCard);
		return new SuccessResult(Messages.CreditCardDeleted);
	}
	
	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {

		var result = BusinessRules.run(checkCreditCardNumber(updateCreditCardRequest.getCardNumber()),
				checkCreditCardCvv(updateCreditCardRequest.getCvv()), 
				checkCreditCardExpiryDate(updateCreditCardRequest.getExpiryDate()));

		if (result != null) {
			return result;
		}
		
		Customer customer=new Customer();
		customer.setId(updateCreditCardRequest.getCustomerId());

		CreditCard creditCard=new CreditCard();
		creditCard.setCreditCardId(updateCreditCardRequest.getCreditCardId());
		creditCard.setCardNumber(updateCreditCardRequest.getCardNumber());
		creditCard.setName(updateCreditCardRequest.getName());
		creditCard.setExpiryDate(updateCreditCardRequest.getExpiryDate());
		creditCard.setCvv(updateCreditCardRequest.getCvv());
		creditCard.setCustomer(customer);
		
		this.creditCardDao.save(creditCard);
		
		return new SuccessResult(Messages.CreditCardUpdated);
	}

	@Override
	public DataResult<List<CreditCardDetailDto>> getByCustomerId(int customerId) {
		
		List<CreditCard> creditCards = this.creditCardDao.getByCustomer_id(customerId);
		 
		 List<CreditCardDetailDto> creditCardDetailDtos = creditCards.stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CreditCardDetailDto>>(creditCardDetailDtos);
	}
	
	private Result checkCreditCardNumber(String creditCardNumber) {
		
		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
		        "(?<mastercard>5[1-5][0-9]{14})|" +
		        "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
		        "(?<amex>3[47][0-9]{13})|" +
		        "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
		        "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(creditCardNumber);

		if (!matcher.matches()) {
			return new ErrorResult(Messages.InvalidCreditCard);
		}
		return new SuccessResult();
	}
	
	private Result checkCreditCardCvv(String cvv) {
		String regex = "^[0-9]{3,3}$";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(cvv);

		if (!matcher.matches()) {
			return new ErrorResult(Messages.InvalidCreditCard);
		}
		return new SuccessResult();
	}
	private Result checkCreditCardExpiryDate(String expiryDate) {
		String regex = "^(0[1-9]|1[0-2])/?(([0-9]{4}|[0-9]{2})$)";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(expiryDate);
		
		if (!matcher.matches()) {
			return new ErrorResult(Messages.InvalidCreditCard);
		}
		return new SuccessResult();
	}

	
	
	

}
