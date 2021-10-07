package com.etiya.ReCapProject.entities.requests.rental;

import java.util.Date;
import java.util.List;

import com.etiya.ReCapProject.entities.dtos.AdditionalServiceForRentalDto;
import com.etiya.ReCapProject.entities.dtos.CreditCardDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@JsonIgnore
	private int id;
	
	private int carId;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private Date rentDate;
	
	private Date returnDate;
	
	private String returnLocation;
	
	private CreditCardDetailDto creditCardDetailDto;
	
	private boolean shouldWeSaveCard;
	
	private int creditCardId;
	
	private List<AdditionalServiceForRentalDto> additionalServiceForRentalDtos;
}
