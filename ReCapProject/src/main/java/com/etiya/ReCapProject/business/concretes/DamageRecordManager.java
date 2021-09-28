package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.DamageRecordService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.DamageRecordDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.DamageRecord;
import com.etiya.ReCapProject.entities.requests.CreateDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.DeleteDamageRecordRequest;
import com.etiya.ReCapProject.entities.requests.UpdateDamageRecordRequest;

@Service
public class DamageRecordManager implements DamageRecordService {

	private DamageRecordDao damageRecordDao;
	
	@Autowired
	public DamageRecordManager(DamageRecordDao damageRecordDao) {
		super();
		this.damageRecordDao = damageRecordDao;
	}

	@Override
	public DataResult<List<DamageRecord>> getAll() {
		return new SuccessDataResult<List<DamageRecord>>(this.damageRecordDao.findAll());
	}

	@Override
	public DataResult<DamageRecord> getById(int id) {
		return new SuccessDataResult<DamageRecord>(this.damageRecordDao.getById(id));
	}

	@Override
	public Result add(CreateDamageRecordRequest createDamageRecordRequest) {
	Car car = new Car();
	car.setCarId(createDamageRecordRequest.getCarId());
	
	DamageRecord damageRecord = new DamageRecord();
	damageRecord.setDamageInformation(createDamageRecordRequest.getDamageInformation());
	damageRecord.setCar(car);
	
	this.damageRecordDao.save(damageRecord);
	return new SuccessResult(Messages.ADD);
	}

	@Override
	public Result delete(DeleteDamageRecordRequest deleteDamageRecordRequest) {
		DamageRecord damageRecord = new DamageRecord();
		damageRecord.setId(deleteDamageRecordRequest.getId());
		
		this.damageRecordDao.delete(damageRecord);
		return new SuccessResult(Messages.DELETE);
	}

	@Override
	public Result update(UpdateDamageRecordRequest updateDamageRecordRequest) {
		Car car = new Car();
		car.setCarId(updateDamageRecordRequest.getCarId());
		
		DamageRecord damageRecord = new DamageRecord();
		damageRecord.setId(updateDamageRecordRequest.getId());
		damageRecord.setDamageInformation(updateDamageRecordRequest.getDamageInformation());
		damageRecord.setCar(car);
		
		this.damageRecordDao.save(damageRecord);
		return new SuccessResult(Messages.UPDATE);
	}

}
