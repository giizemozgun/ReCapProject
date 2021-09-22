package com.etiya.ReCapProject.entities.concretes;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.etiya.ReCapProject.core.entities.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationUser extends User{


}	
	

