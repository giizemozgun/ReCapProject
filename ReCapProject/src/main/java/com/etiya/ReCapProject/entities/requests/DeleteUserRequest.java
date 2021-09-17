package com.etiya.ReCapProject.entities.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {
	
	private int id;
	
	private String firstName;
}
