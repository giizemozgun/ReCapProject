package com.etiya.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindexScoreDto {
	
	private int customerId;
	
	private int carId;
	
	private int findexScore;
	
	private int minFindexScore;
}
