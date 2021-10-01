package com.etiya.ReCapProject.entities.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDetailDto {
	

	@NotNull
	private String name;
	
	@NotNull
	@Size(min=16, max=16)
	private String cardNumber;
	
	@NotNull
	private String expiryDate;
	
	@NotNull
	@Size(min=3,max=3)
	private String cvv;
}
