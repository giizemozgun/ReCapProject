package com.etiya.ReCapProject.entities.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServiceDto {
	
	private int id;
	
	@JsonIgnore
	private double dailyPrice;
}
