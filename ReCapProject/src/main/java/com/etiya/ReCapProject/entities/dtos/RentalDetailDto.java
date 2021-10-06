package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDetailDto {

	private String carName;
	
	private Date rentDate;

	private Date returnDate;

	private String pickUpLocation;

	private String returnLocation;

	private int pickUpKm;

	private double totalAmount;

	private List<AdditionalServiceDetailDto> additionalServiceDetailDtos;
	
	private int customerId;

}
