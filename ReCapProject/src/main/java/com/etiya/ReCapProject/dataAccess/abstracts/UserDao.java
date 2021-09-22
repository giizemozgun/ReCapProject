package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.ApplicationUser;


public interface UserDao extends JpaRepository<ApplicationUser, Integer> {
	
	ApplicationUser getByEmail(String email);
	
	ApplicationUser getByPassword(String password);
	
	boolean existsByEmail(String email);
	
	boolean existsByPassword(String password);
}
