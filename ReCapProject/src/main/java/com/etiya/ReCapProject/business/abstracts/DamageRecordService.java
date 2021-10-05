package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.dtos.DamageRecordDetailDto;
import com.etiya.ReCapProject.entities.requests.damageRecord.CreateDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.damageRecord.DeleteDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.damageRecord.UpdateDamageRecordRequest;


public interface DamageRecordService {
	
	DataResult<List<DamageRecordDetailDto>> getAll();
	DataResult<DamageRecordDetailDto> getById(int id);
	Result add(CreateDamageRecordRequest createDamageRecordRequest);
	Result delete(DeleteDamageRecordRequest deleteDamageRecordRequest);
	Result update(UpdateDamageRecordRequest updateDamageRecordRequest);
}
