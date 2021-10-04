package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDetailDto {
	
	private int maintenanceId;
	
	private Date maintenanceDate;
	
	private Date returnDate;

	private String carName;
	
	
}
