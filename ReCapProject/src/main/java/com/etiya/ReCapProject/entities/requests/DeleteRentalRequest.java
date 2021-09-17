package com.etiya.ReCapProject.entities.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DeleteRentalRequest {
	
	private int id;
	
	private int carId;
	
	private String carName;
	
	
}	
