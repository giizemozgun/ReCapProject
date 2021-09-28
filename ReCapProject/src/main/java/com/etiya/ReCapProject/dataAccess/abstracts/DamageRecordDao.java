package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.DamageRecord;

public interface DamageRecordDao extends JpaRepository<DamageRecord, Integer>{

}
