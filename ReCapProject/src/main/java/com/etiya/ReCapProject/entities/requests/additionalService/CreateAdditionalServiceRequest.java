package com.etiya.ReCapProject.entities.requests.additionalService;

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
public class CreateAdditionalServiceRequest {
	
	@JsonIgnore
	private int id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@NotNull
	private double dailyPrice;
	
}