package com.etiya.ReCapProject.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForRegisterDto {
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String firstName;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Email
	private String email;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=6, max=10)
	private String password;

		
}
