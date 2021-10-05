package com.etiya.ReCapProject.entities.requests.carImage;


import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	
	@NotNull
	private int id;
	
	private int carId;

	
	private MultipartFile file;
}
