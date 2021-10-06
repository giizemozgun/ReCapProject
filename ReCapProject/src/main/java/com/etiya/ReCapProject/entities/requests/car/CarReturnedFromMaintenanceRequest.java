package com.etiya.ReCapProject.entities.requests.car;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarReturnedFromMaintenanceRequest {
	
	@JsonIgnore
	private int carId;
	
	private int maintenanceId;
	
	
}
