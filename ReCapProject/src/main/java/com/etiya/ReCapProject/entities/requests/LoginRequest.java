package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
}
