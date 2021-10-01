package com.etiya.ReCapProject.entities.requests.update;

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
	

	private int price;
	
	private int rentalId;
}
