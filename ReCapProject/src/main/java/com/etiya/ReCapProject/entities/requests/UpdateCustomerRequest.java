package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
	
	
	
	@NotNull
	private int id;
	
	
	
	@NotNull
	@Size(min=2, max=30)
	private String companyName;
	
	@NotNull
	private int userId;
	
	
}
