package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.abstracts.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
	
}
