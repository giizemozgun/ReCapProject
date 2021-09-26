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
public class UpdateMaintenanceRequest {
	
	@NotNull
	private int maintenanceId;
	
	private Date maintenanceDate;
	
	private Date returnDate;
	
	private int carId;
}
