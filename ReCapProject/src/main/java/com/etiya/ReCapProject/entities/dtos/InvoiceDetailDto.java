package com.etiya.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceDetailDto {
	private String invoiceNumber;
	private int customerId;
	
	
}
