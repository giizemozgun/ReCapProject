package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	
	@NotNull
	private String name;
	
	@NotNull
	private String cardNumber;
	
	@NotNull
	private String expiryDate;
	
	@NotNull
	private String cvv;
	
	@NotNull
	private int customerId;
	
}
