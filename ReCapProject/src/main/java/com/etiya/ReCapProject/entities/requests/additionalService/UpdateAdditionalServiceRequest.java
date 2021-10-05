package com.etiya.ReCapProject.entities.requests.additionalService;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	@NotNull
	private int id;
	
	private String name;

	private String description;
	
	private double dailyPrice;

	private int rentalId;
	
}
