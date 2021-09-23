package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.PaymentService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Payment;
import com.etiya.ReCapProject.entities.requests.CreatePaymentRequest;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
	
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentsController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
		
	return this.paymentService.add(createPaymentRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Payment>> getAll(){
		
		return this.paymentService.getAll();
	}
	
}
