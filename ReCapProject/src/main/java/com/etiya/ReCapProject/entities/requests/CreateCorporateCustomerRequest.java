package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class CreateCorporateCustomerRequest {
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=10, max=10)
	private String taxNumber;
	
	@Email
	private String email;
	
	@NotNull
	@Size(min=2, max=10)
	private String password;
	
	
	private String companyName;
	
	
	
	
	
	
	
	
}

