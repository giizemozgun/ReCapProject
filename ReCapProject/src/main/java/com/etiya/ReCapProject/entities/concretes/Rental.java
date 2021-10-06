package com.etiya.ReCapProject.entities.concretes;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.etiya.ReCapProject.entities.abstracts.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rentals")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="rent_date")
	private Date rentDate;
	
	@Column(name="return_date")
	@Nullable
	private Date returnDate;
	
	@Column(name = "is_car_returned")
	private boolean isCarReturned = false;
	
	@Column(name="pick_up_location")
	private String pickUpLocation;

	@Column(name="return_location")
	private String returnLocation;
	
	@Column(name="pick_up_km")
	private int pickUpKm;
	
	@Column(name="return_km")
	private int returnKm;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToMany()
	@JoinTable(name = "rental_additional_services", 
	joinColumns = @JoinColumn(name = "rental_id"), 
	inverseJoinColumns = @JoinColumn(name = "additional_service_id"))
	private List<AdditionalService> additionalServices;
	

	
}
