package com.etiya.ReCapProject.entities.dtos;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarMaintenanceDetailDto {
	

	private int carId;
	private int maintenanceId;
	private Date maintenanceDate;
	private Date returnDate;
		
}
