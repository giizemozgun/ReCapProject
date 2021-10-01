package com.etiya.ReCapProject.business.abstracts;


import com.etiya.ReCapProject.entities.requests.PosServiceRequest;

public interface PosService {
	
	boolean isCreditCardLimitExceeded(PosServiceRequest posServiceRequest);
}
