package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.DamageRecordService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.DamageRecordDao;
import com.etiya.ReCapProject.entities.concretes.DamageRecord;
import com.etiya.ReCapProject.entities.dtos.DamageRecordDetailDto;
import com.etiya.ReCapProject.entities.requests.damageRecord.CreateDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.damageRecord.DeleteDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.damageRecord.UpdateDamageRecordRequest;

@Service
public class DamageRecordManager implements DamageRecordService {

	private DamageRecordDao damageRecordDao;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public DamageRecordManager(DamageRecordDao damageRecordDao,ModelMapper modelMapper) {
		super();
		this.damageRecordDao = damageRecordDao;
		this.modelMapper = modelMapper;
		
	}

	@Override
	public DataResult<List<DamageRecordDetailDto>> getAll() {
		List<DamageRecord> damageRecords = this.damageRecordDao.findAll();
		 
		List<DamageRecordDetailDto> damageRecordDetailDtos =damageRecords.stream().map(damageRecord -> modelMapper.map(damageRecord, DamageRecordDetailDto.class)).collect(Collectors.toList());
	        return new SuccessDataResult<List<DamageRecordDetailDto>>(damageRecordDetailDtos);
	}

	@Override
	public DataResult<DamageRecordDetailDto> getById(int id) {
		DamageRecord damageRecord = this.damageRecordDao.getById(id);
		DamageRecordDetailDto damageRecordDetailDto = modelMapper.map(damageRecord,DamageRecordDetailDto.class);
		
		return new SuccessDataResult<DamageRecordDetailDto>(damageRecordDetailDto);
	}

	@Override
	public Result add(CreateDamageRecordRequest createDamageRecordRequest) {
		
		DamageRecord damageRecord = modelMapper.map(createDamageRecordRequest, DamageRecord.class);
	
		this.damageRecordDao.save(damageRecord);
		return new SuccessResult(Messages.DamageRecordAdded);
	}

	@Override
	public Result delete(DeleteDamageRecordRequest deleteDamageRecordRequest) {
		
		DamageRecord damageRecord = modelMapper.map(deleteDamageRecordRequest, DamageRecord.class);
		this.damageRecordDao.delete(damageRecord);
		return new SuccessResult(Messages.DamageRecordDeleted);
	}

	@Override
	public Result update(UpdateDamageRecordRequest updateDamageRecordRequest) {
		
		DamageRecord damageRecord = modelMapper.map(updateDamageRecordRequest, DamageRecord.class);
		
		this.damageRecordDao.save(damageRecord);
		return new SuccessResult(Messages.DamageRecordUpdated);
	}

}
