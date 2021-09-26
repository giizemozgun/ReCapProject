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

import com.etiya.ReCapProject.business.abstracts.MaintenanceService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Maintenance;
import com.etiya.ReCapProject.entities.requests.CreateMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.DeleteMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.UpdateMaintenanceRequest;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	
	private MaintenanceService maintenanceService;

	@Autowired
	public MaintenancesController(MaintenanceService maintenanceService) {
		super();
		this.maintenanceService = maintenanceService;
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		
	return this.maintenanceService.add(createMaintenanceRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Maintenance>> getAll(){
		
		return this.maintenanceService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Maintenance> getById(int id){
		return this.maintenanceService.getById(id);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceService.delete(deleteMaintenanceRequest);
	}
	
	
}
