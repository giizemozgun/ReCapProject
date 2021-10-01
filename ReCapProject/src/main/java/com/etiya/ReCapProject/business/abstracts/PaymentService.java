package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Payment;
import com.etiya.ReCapProject.entities.requests.CreatePaymentRequest;

public interface PaymentService {
	
	DataResult<List<Payment>> getAll();
	Result add(CreatePaymentRequest createPaymentRequest);
	
	
}	
