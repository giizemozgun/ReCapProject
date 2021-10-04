package com.etiya.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageRecordDetailDto {
	
	private int id;
	
	private String damageInformation;
	
	private String carName;
	
}
