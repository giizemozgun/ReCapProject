package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.DamageRecord;
import com.etiya.ReCapProject.entities.requests.create.CreateDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateDamageRecordRequest;


public interface DamageRecordService {
	
	DataResult<List<DamageRecord>> getAll();
	DataResult<DamageRecord> getById(int id);
	Result add(CreateDamageRecordRequest createDamageRecordRequest);
	Result delete(DeleteDamageRecordRequest deleteDamageRecordRequest);
	Result update(UpdateDamageRecordRequest updateDamageRecordRequest);
}
