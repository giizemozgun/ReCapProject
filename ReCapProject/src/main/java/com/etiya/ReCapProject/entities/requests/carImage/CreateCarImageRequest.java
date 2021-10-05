package com.etiya.ReCapProject.entities.requests.carImage;



import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

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
public class CreateCarImageRequest {
	
	@NotNull
	private int carId;
	
	@NotNull
	private String imagePath;
	
	@NotNull
	@NotBlank
	@JsonIgnore
	private MultipartFile file;
	
	
	
}
