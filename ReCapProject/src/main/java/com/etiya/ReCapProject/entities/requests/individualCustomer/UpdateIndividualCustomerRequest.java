package com.etiya.ReCapProject.entities.requests.individualCustomer;

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
public class UpdateIndividualCustomerRequest {
	
	
	@NotNull
	private int id;
	
	@NotNull
	@Size(min=11, max=11)
	private String identityNumber;

	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String firstName;

	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	
	@Email
	private String email;
	
	@NotNull
	@Size(min=2, max=10)
	private String password;
	
	
	
	
}
