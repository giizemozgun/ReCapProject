package com.etiya.ReCapProject.entities.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	@NotNull
	private int id;

	private int customerId;
	
	private int carId;
	
	@NotNull
	private Date rentDate;

	private Date returnDate;

	@NotNull
	private String returnLocation;
	
	@NotNull
	int returnKm;
}
