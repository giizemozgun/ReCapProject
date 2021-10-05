package com.etiya.ReCapProject.entities.requests.damageRecord;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageRecordRequest {
	
	@NotNull
	private String damageInformation;
	
	private int carId;
}
