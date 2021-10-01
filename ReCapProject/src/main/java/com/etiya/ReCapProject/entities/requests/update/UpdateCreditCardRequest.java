package com.etiya.ReCapProject.entities.requests.update;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
	@NotNull
	private int creditCardId;
	
	private String name;
	
	private String cardNumber;

	private String expiryDate;

	private String cvv;
	
	private int customerId;
}
