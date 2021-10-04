package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;

public interface RentalDao extends JpaRepository<Rental, Integer> {

	List<Rental> getByCar_CarId(int carId);

	boolean existsByIsCarReturnedIsFalseAndCar_CarId(int carId);
	
	List<RentalDetailDto> getByCustomer_Id(int customerId);
	
}
