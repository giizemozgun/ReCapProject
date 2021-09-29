package com.etiya.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cars")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rentals"})
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;
	
	@Column(name="car_name")
	private String carName;

	@Column(name="model_years")
	private int modelYear;
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="description")
	private String description;
	
	@Column(name="min_findex_score")
	private int minFindexScore;
	
	@Column(name="km")
	private int km;
	
	@Column(name="city")
	private String city;
	
	@Column(name="is_Available" , columnDefinition = "boolean default true")
	private boolean isAvailable;
	
	@OneToMany(mappedBy = "car")
	@JsonIgnore
	private List<Rental> rentals;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car")
	@JsonIgnore
	private List<CarImage> carImages;
	
	@OneToMany(mappedBy="car")
	@JsonIgnore
	private List<Maintenance> maintenances;
	
	@OneToMany(mappedBy= "car")
	@JsonIgnore
	private List<DamageRecord> damagerecords;
	
}


