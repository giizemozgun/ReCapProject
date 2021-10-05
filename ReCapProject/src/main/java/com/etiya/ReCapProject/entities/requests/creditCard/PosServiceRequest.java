package com.etiya.ReCapProject.entities.requests.creditCard;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosServiceRequest {

	
	@NotNull
	private String name;
	
	@NotNull
	private String cardNumber;
	
	@NotNull
	private String expiryDate;
	
	@NotNull
	private String cvv;
	
	@NotNull
	private double paymentAmount;
	
	
	
}
