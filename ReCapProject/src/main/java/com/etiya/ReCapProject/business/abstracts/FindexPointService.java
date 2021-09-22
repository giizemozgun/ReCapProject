package com.etiya.ReCapProject.business.abstracts;


public interface FindexPointService {
	
	int getIndividualCustomerFindexPoint(String identityNumber);	
	int getCorporateCustomerFindexPoint(String taxNumber);
	
	
}
