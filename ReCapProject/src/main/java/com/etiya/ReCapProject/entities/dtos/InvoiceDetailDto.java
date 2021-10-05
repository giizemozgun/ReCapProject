package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceDetailDto {
	
	private int invoiceId;
	
	private String invoiceNumber;
	
	private Date invoiceDate;
	
	private int totalRentalDay;
	
	private double totalAmount;
	
	private int rentalId;
	
	
}
