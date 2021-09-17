package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCustomerRequest {
	
	private int id;
	
	private String companyName;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	private int userId;
}
