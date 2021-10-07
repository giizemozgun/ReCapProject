package com.etiya.ReCapProject.core.services;

import java.util.Random;

public class FindexService {

	public int getIndividualCustomerFindexPoint(String identityNumber) {

		Random random = new Random();
		return random.nextInt(1900);

	}

	public int getCorporateCustomerFindexPoint(String taxNumber) {

		Random random = new Random();
		return random.nextInt(1900);

	}
}
