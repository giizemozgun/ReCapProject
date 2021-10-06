package com.etiya.ReCapProject.entities.requests.invoice;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	@JsonIgnore
	private int invoiceId;
	
	@NotNull
	private int invoiceNumber;	
	
	@NotNull
	private int rentalId;
	
	
	
	

	
}
