package com.etiya.ReCapProject.entities.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceBetweenDateRequest {
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
}
