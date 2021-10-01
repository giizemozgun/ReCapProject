package com.etiya.ReCapProject.business.concretes;

import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.PosService;
import com.etiya.ReCapProject.core.services.FakePosService;
import com.etiya.ReCapProject.entities.requests.PosServiceRequest;

@Service
public class PosAdapterManager implements PosService{

	@Override
	public boolean isCreditCardLimitExceeded(PosServiceRequest posServiceRequest) {
		FakePosService fakePosService = new FakePosService();
		
		return fakePosService.isCreditCardLimitExceeded(posServiceRequest.getCardNumber(), posServiceRequest.getName(), 
				posServiceRequest.getExpiryDate(), posServiceRequest.getCvv(), posServiceRequest.getPaymentAmount());
	}
	
	
}
