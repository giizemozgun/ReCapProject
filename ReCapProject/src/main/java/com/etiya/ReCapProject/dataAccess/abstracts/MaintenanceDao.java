package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Maintenance;

public interface MaintenanceDao extends JpaRepository<Maintenance, Integer> {
	
	List<Maintenance> getByCar_CarId(int carId);
}
