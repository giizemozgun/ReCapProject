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
public class UpdateRentalRequest {
	

	
	@NotNull
	private int id;
	
	private int carId;
	
	private String carName;
	
	@NotNull
	private Date rentDate;
	
	
	private Date returnDate;
	
	
	
}
