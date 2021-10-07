package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
		

	List<CreditCard> getByCustomer_id(int customerId);
	
	boolean existsByCreditCardId(int creditCardId);
	
}
