package com.etiya.ReCapProject.entities.requests.car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class CreateCarRequest {
	
	@JsonIgnore
	private int carId;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String carName;

	@NotNull
	private int brandId;
	
	@NotNull
	private int colorId;
	
	@NotNull
	private int modelYear;
	
	@NotNull
	private String city;
	
	@NotNull
	private int km;
	
	@Min(0)
	@NotNull
	private double dailyPrice;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	private String description;
	
	@NotNull
	private int minFindexScore;
	
}
