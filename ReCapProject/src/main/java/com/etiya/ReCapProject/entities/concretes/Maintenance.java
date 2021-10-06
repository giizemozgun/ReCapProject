package com.etiya.ReCapProject.entities.concretes;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="maintenance")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maintenance_id")
	private int maintenanceId;
	
	@Column(name="maintenance_date")
	private Date maintenanceDate;
	
	@Column(name="return_date")
	@Nullable
	private Date returnDate;
	
	@Column(name = "is_car_returned")
	private boolean isCarReturned = false;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
}
