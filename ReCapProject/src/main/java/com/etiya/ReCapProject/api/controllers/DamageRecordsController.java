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

import com.etiya.ReCapProject.business.abstracts.DamageRecordService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.DamageRecord;
import com.etiya.ReCapProject.entities.requests.create.CreateDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateDamageRecordRequest;

@RestController
@RequestMapping("/api/damagerecords")
public class DamageRecordsController {
	
	private DamageRecordService damageRecordService;

	@Autowired
	public DamageRecordsController(DamageRecordService damageRecordService) {
		super();
		this.damageRecordService = damageRecordService;
	}
	
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateDamageRecordRequest createDamageRecordRequest) {
		
	return this.damageRecordService.add(createDamageRecordRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<DamageRecord>> getAll(){
		
		return this.damageRecordService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<DamageRecord> getById(int id){
		return this.damageRecordService.getById(id);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateDamageRecordRequest updateDamageRecordRequest) {
		return this.damageRecordService.update(updateDamageRecordRequest);
	}
	
	@PutMapping("/delete")
	public Result delete(@Valid @RequestBody DeleteDamageRecordRequest deleteDamageRecordRequest) {
		return this.damageRecordService.delete(deleteDamageRecordRequest);
	}
	
}
