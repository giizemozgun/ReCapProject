package com.etiya.ReCapProject.core.services;

import java.util.Random;

public class FakePosService {
	
public boolean isCreditCardLimitExceeded(String cardNumber,String cardHolderName,String expiryDate, String cvv, double paymentAmount) {
		
		Random limit = new Random();        
		if(paymentAmount<=limit.nextInt(4000)) {
			return true;
		}
		return false;
		
}
}
