package com.etiya.ReCapProject.entities.requests.car;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarReturnedRequest {
	
	@JsonIgnore
	private int carId;
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private int returnKm;
	
}
