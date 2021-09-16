package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.core.entities.User;


public interface UserDao extends JpaRepository<User, Integer> {

}
