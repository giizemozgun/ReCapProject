package com.etiya.ReCapProject.entities.requests.maintenance;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {
	
	@NotNull
	private int maintenanceId;
	
}
