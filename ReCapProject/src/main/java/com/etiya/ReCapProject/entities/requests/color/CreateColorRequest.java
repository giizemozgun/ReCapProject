package com.etiya.ReCapProject.entities.requests.color;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
	
	@JsonIgnore
	private int colorId;
	
	@NotBlank(message="Boş olamaz")
	@NotNull
	@Size(min=2, max=30)
	private String colorName;
}
