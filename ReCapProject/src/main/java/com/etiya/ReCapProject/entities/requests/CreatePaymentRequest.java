package com.etiya.ReCapProject.entities.requests;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	private int rentalId;
	
	private int creditCardId;
	
	private double totalAmount;
	
}
