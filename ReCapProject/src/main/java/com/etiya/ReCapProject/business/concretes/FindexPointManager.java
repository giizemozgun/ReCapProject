package com.etiya.ReCapProject.business.concretes;

import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.FindexPointService;
import com.etiya.ReCapProject.core.services.FindexService;

@Service
public class FindexPointManager implements FindexPointService {

	@Override
	public int getIndividualCustomerFindexPoint(String identityNumber) {
		FindexService findexService = new FindexService();
		return findexService.getIndividualCustomerFindexPoint(identityNumber);
	}

	@Override
	public int getCorporateCustomerFindexPoint(String taxNumber) {
		FindexService findexService = new FindexService();
		return findexService.getCorporateCustomerFindexPoint(taxNumber);
	}

	
	
	
}
