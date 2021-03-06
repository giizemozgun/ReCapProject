package com.etiya.ReCapProject.entities.requests.creditCard;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	
	@JsonIgnore
	private int creditCardId;
	
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
