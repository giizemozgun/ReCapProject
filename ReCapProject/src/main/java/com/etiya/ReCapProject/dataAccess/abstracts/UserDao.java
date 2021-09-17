package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.ApplicationUser;


public interface UserDao extends JpaRepository<ApplicationUser, Integer> {

}
