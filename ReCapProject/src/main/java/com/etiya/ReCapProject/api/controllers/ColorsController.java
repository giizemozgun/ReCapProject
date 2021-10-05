package com.etiya.ReCapProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.ColorDetailDto;
import com.etiya.ReCapProject.entities.requests.color.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.color.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.color.UpdateColorRequest;

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
	public Result add(@Valid @RequestBody CreateColorRequest createColorRequest) {
		
	 return this.colorService.add(createColorRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<ColorDetailDto>> getAll(){
		
		return this.colorService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<ColorDetailDto> getById( int colorId){
		return this.colorService.getById(colorId);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	
}
