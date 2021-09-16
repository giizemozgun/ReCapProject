package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.ColorService;

import com.etiya.ReCapProject.entities.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Color color) {
		
	 this.colorService.add(color);
	}
	
	@GetMapping("/getall")
	public List<Color> getAll(){
		
		return this.colorService.getAll();
	}
	
	@GetMapping("/getById")
	public Color getById( int colorId){
		return this.colorService.getById(colorId);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Color color) {
		this.colorService.update(color);
	}
	
	@PutMapping("/delete")
	public void delete(@RequestBody Color color) {
		this.colorService.delete(color);
	}
	
}
