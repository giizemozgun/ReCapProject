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
public class UpdateUserRequest {
	

	@NotNull
	private int id;

	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Email
	private String Email;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=6, max=10)
	private String password;
}
