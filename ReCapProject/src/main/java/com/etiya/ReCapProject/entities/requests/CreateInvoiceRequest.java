package com.etiya.ReCapProject.entities.requests;


import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	@NotNull
	private int invoiceNumber;	
	
	@NotNull
	private int rentalId;
	
	
	

	
}
