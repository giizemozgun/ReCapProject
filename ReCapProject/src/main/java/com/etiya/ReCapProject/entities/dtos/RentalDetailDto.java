package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.etiya.ReCapProject.entities.concretes.AdditionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDetailDto {
		
	private CarDetailDto carDetailDto;
	
	private Date rentDate;
	
	private Date returnDate;
	
	private String pickUpLocation;

	private String returnLocation;
	
	private int pickUpKm;
	
	private int totalAmount;
	
	private List<AdditionalService> additionalServices;

}
