package com.etiya.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDetailDto {
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String identityNumber;
	
}
