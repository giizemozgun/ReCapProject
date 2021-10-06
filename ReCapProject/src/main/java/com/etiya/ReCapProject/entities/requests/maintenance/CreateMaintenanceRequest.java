package com.etiya.ReCapProject.entities.requests.maintenance;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {

	@JsonIgnore
	private int maintenanceId;
	
	@NotNull
	private Date maintenanceDate;

	@Nullable
	private Date returnDate;

	@NotNull
	private int carId;

}
