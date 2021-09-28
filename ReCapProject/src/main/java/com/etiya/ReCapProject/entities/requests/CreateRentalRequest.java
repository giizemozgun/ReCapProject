package com.etiya.ReCapProject.entities.requests;

import java.util.Date;


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
	
	private int carId;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private Date rentDate;
	
	private Date returnDate;
	
	private String returnLocation;
}
